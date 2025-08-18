package tech.project.rental_company.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tech.project.rental_company.exception.VehicleNotFoundException
import tech.project.rental_company.model.enums.VehicleStatusEnum
import tech.project.rental_company.model.vehicle.VehicleDTO
import tech.project.rental_company.repository.VehicleRepository
import java.time.LocalDateTime


@Service
class VehicleService(
    private val vehicleRepository: VehicleRepository
) {

    fun findByPublicId(publicId: String): VehicleDTO {
        val vehicle = vehicleRepository.findByPublicId(publicId)
            .orElseThrow { VehicleNotFoundException(reason = "Veículo com ID '$publicId' não encontrado.") }

        return VehicleDTO.build(vehicle)
    }

    fun findAll(): List<VehicleDTO> {
        val vehicles = vehicleRepository.findAll()
        return vehicles.map { vehicle -> VehicleDTO.build(vehicle) }
    }

    fun findVehicleByLicensePlate(licensePlate: String): VehicleDTO {

        val vehicle = vehicleRepository.findByLicensePlate(licensePlate).orElseThrow {
            VehicleNotFoundException(reason = "Veículo com a placa '$licensePlate' não encontrado no banco de dados.")
        }

        return VehicleDTO.build(vehicle)
    }

    @Transactional
    fun updateVehicleStatus(licensePlate: String, newStatus: VehicleStatusEnum): VehicleDTO {

        val vehicle = vehicleRepository.findByLicensePlate(licensePlate)
            .orElseThrow { VehicleNotFoundException(reason = "Veículo com placa '$licensePlate' não encontrado.") }

        val updatedVehicle = vehicle.apply {
            vehicleStatus = newStatus
            updateDate = LocalDateTime.now()
        }

        val savedVehicle = vehicleRepository.save(updatedVehicle)

        return VehicleDTO.build(savedVehicle)
    }
}
