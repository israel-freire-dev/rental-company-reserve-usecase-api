package tech.project.rental_company.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tech.project.rental_company.exception.CustomerHasPendencyException
import tech.project.rental_company.exception.CustomerIsSuspendedException
import tech.project.rental_company.exception.ReserveCanceledException
import tech.project.rental_company.exception.ReserveNotFoundException
import tech.project.rental_company.exception.ReserveRentalDateException
import tech.project.rental_company.exception.VehicleReservedException
import tech.project.rental_company.exception.VehicleUnderMaintenanceException
import tech.project.rental_company.model.enums.PaymentStatusEnum.*
import tech.project.rental_company.model.enums.ReservationStatusEnum
import tech.project.rental_company.model.enums.VehicleStatusEnum
import tech.project.rental_company.model.reserve.CustomerSnapshot
import tech.project.rental_company.model.reserve.Reserve
import tech.project.rental_company.model.reserve.ReserveDTO
import tech.project.rental_company.model.reserve.ReserveRequest
import tech.project.rental_company.repository.ReserveRepository
import java.time.temporal.ChronoUnit

@Service
class ReserveService(
    private val reserveRepository: ReserveRepository,
    private val customerService: CustomerService,
    private val vehicleService: VehicleService

) {

    fun findAllReserves(): List<ReserveDTO> {
        val reserves = reserveRepository.findAll()
        return reserves.map { reserve ->
            ReserveDTO.build(reserve)
        }
    }

    fun findByPublicId(publicId: String): ReserveDTO {
        val reserve = reserveRepository.findByPublicId(publicId).orElseThrow {
            EntityNotFoundException("Reserva não encontrada publicId={$publicId}.")
        }
        return ReserveDTO.build(reserve)
    }

    fun findByCustomerName(customerName: String): ReserveDTO {
        val customer = customerService.findCustomerByName(customerName).let {
            CustomerSnapshot(
                name = it.name, document = it.document
            )
        }
        val reserve = reserveRepository.findByCustomerData(customer)
        return ReserveDTO.build(reserve)
    }

    @Transactional
    fun createReserve(request: ReserveRequest): ReserveDTO {

        val customer = customerService.findCustomerByDocument(
            request.customerData.document
        )
        val vehicle = vehicleService.findVehicleByLicensePlate(
            request.vehicleData.licensePlate
        )

        if (customer.isSuspended) {
            throw CustomerIsSuspendedException()
        }

        if (customer.hasPendency) {
            throw CustomerHasPendencyException()
        }

        if (vehicle.vehicleStatus != VehicleStatusEnum.AVAILABLE.toString()) {
            when (vehicle.vehicleStatus) {
                VehicleStatusEnum.RESERVED.toString() -> {
                    throw VehicleReservedException(
                        reason = "O veículo com placa ${vehicle.licensePlate} já está reservado"
                    )
                }

                VehicleStatusEnum.UNDER_MAINTENANCE.toString() -> {
                    throw VehicleUnderMaintenanceException(
                        reason = "O veículo com placa ${vehicle.licensePlate} está em manutenção"
                    )
                }
            }
        }

        val newReserve = Reserve(
            customerData = request.customerData,
            vehicleData = request.vehicleData,
            amountInCents = calculateQuote(request),
            rentalDate = request.rentalDate,
            returnDate = request.returnDate,
            reservationStatus = ReservationStatusEnum.ACTIVE,
            paymentStatus = PENDING

        )

        reserveRepository.save(newReserve)

        vehicleService.updateVehicleStatus(
            vehicle.licensePlate, VehicleStatusEnum.RESERVED
        )
        customerService.updateCustomerPendencyStatus(
            customer.customerId, true
        )
        return ReserveDTO.build(newReserve)
    }


    @Transactional
    fun cancelReserve(reserveId: String): ReserveDTO {

        val reserve = reserveRepository.findByPublicId(reserveId).orElseThrow {
            throw ReserveNotFoundException(reason = "Reserva não encontrada no sistema reserveId={$reserveId}")
        }

        val customer = customerService.findCustomerByDocument(
            reserve.customerData.document
        )

        val vehicle = vehicleService.findVehicleByLicensePlate(
            reserve.vehicleData.licensePlate
        )

        when (reserve.paymentStatus) {
            CANCELED -> throw ReserveCanceledException()

            PAID -> reserve.apply {
                reserve.paymentStatus = REFUNDED
                reserve.reservationStatus = ReservationStatusEnum.CANCELED
            }

            PENDING -> reserve.apply {
                reserve.paymentStatus = CANCELED
                reserve.reservationStatus = ReservationStatusEnum.CANCELED
            }

            REFUNDED -> reserve.apply { reserve.reservationStatus = ReservationStatusEnum.CANCELED }
        }

        reserveRepository.save(reserve)

        customerService.updateCustomerPendencyStatus(
            customer.customerId, false
        )
        vehicleService.updateVehicleStatus(
            vehicle.licensePlate, VehicleStatusEnum.AVAILABLE
        )
        return ReserveDTO.build(reserve)
    }

    fun completeReserve(reserveId: String): ReserveDTO {

        val reserve = reserveRepository.findByPublicId(reserveId).orElseThrow {
            throw ReserveNotFoundException(reason = "Reserva não encontrada no sistema reserveId={$reserveId}")
        }

        val customer = customerService.findCustomerByDocument(
            reserve.customerData.document
        )

        val vehicle = vehicleService.findVehicleByLicensePlate(
            reserve.vehicleData.licensePlate
        )

        when (reserve.paymentStatus) {
            PAID -> {
                reserve.apply { reserve.reservationStatus = ReservationStatusEnum.COMPLETED }
            }

            PENDING -> {
                reserve.apply {
                    reservationStatus = ReservationStatusEnum.CANCELED
                }
                throw CustomerHasPendencyException()
            }

            CANCELED -> {
                reserve.apply {
                    reservationStatus = ReservationStatusEnum.CANCELED
                }
                throw ReserveCanceledException()
            }

            REFUNDED -> {
                reserve.apply {
                    reservationStatus = ReservationStatusEnum.CANCELED
                }
                throw ReserveCanceledException()
            }
        }
        reserveRepository.save(reserve)

        customerService.updateCustomerPendencyStatus(
            customer.customerId, false
        )
        vehicleService.updateVehicleStatus(
            vehicle.licensePlate, VehicleStatusEnum.AVAILABLE
        )

        return ReserveDTO.build(reserve)
    }

    private fun calculateQuote(request: ReserveRequest): Long {
        if (request.returnDate.isBefore(request.rentalDate)) {
            throw ReserveRentalDateException()
        }

        val vehicle = vehicleService.findVehicleByLicensePlate(request.vehicleData.licensePlate)

        val numberOfDays = ChronoUnit.DAYS.between(request.rentalDate, request.returnDate) + 1

        val totalAmountInCents = numberOfDays * vehicle.dailyRateInCents

        return totalAmountInCents
    }
}