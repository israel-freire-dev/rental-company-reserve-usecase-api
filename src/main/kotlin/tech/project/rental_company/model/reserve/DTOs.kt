package tech.project.rental_company.model.reserve

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import org.springframework.boot.context.properties.bind.ConstructorBinding
import tech.project.rental_company.model.enums.PaymentStatusEnum
import tech.project.rental_company.model.enums.ReservationStatusEnum
import java.time.LocalDate



data class ReserveDTO(
    val reserveId: String,
    val customerData: CustomerSnapshot,
    val vehicleData: VehicleSnapshot,
    val amountInCents: Long,
    val rentalDate: LocalDate,
    val returnDate: LocalDate,
    var reservationStatus: ReservationStatusEnum,
    var paymentStatus: PaymentStatusEnum
) {
    companion object {
        fun build(reserve: Reserve): ReserveDTO =
            ReserveDTO(
                reserveId = reserve.publicId,
                customerData = reserve.customerData,
                vehicleData = reserve.vehicleData,
                amountInCents= reserve.amountInCents,
                rentalDate = reserve.rentalDate,
                returnDate= reserve.returnDate,
                reservationStatus = reserve.reservationStatus,
                paymentStatus = reserve.paymentStatus
            )
    }
}


data class ReserveRequest(
    val customerData: CustomerSnapshot,
    val vehicleData: VehicleSnapshot,
    val rentalDate: LocalDate,
    val returnDate: LocalDate
)

@Embeddable
data class CustomerSnapshot(
    @NotBlank(message = "O nome do cliente não pode ser vazio.")
    @Size(min = 3, max = 150, message = "O nome do cliente deve ter entre 3 e 150 caracteres.")
    @Column(name = "customer_name", nullable = false)
    val name: String,

    @NotBlank(message = "O documento do cliente não pode ser vazio.")
    @Size(min = 11, max = 14, message = "O documento do cliente deve ter 11 (CPF) ou 14 (CNPJ) dígitos.")
    @Column(name = "customer_document", nullable = false)
    val document: String
)

@Embeddable
data class VehicleSnapshot(

    @NotBlank(message = "O nome/modelo do veículo não pode ser vazio.")
    @Column(name = "vehicle_model_name", nullable = false)
    val modelName: String,

    @Positive(message = "O ano do veículo deve ser um número positivo.")
    @Column(name = "vehicle_year", nullable = false)
    val year: Int,

    @NotBlank(message = "A placa do veículo não pode ser vazia.")
    @Pattern(
        regexp = "[A-Z]{3}[0-9][A-Z][0-9]{2}",
        message = "Formato de placa inválido. Use o padrão Mercosul (ex: ABC1D23)."
    )
    @Column(name = "vehicle_license_plate", nullable = false)
    val licensePlate: String
)