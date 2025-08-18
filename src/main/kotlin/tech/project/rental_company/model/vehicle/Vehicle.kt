package tech.project.rental_company.model.vehicle

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import tech.project.rental_company.model.enums.VehicleFuelTypeEnum
import tech.project.rental_company.model.enums.VehicleStatusEnum
import tech.project.rental_company.model.enums.VehicleTypeEnum
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(schema = "rentalcompany", name = "tb_vehicle")
data class Vehicle(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    val vehicleId: Long? = null,

    @field:NotNull(message = "O ID público não pode ser nulo.")
    @Column(name = "public_id", unique = true, updatable = false, length = 36)
    val publicId: String = UUID.randomUUID().toString(),

    @NotBlank(message = "O nome/modelo do veículo não pode ser vazio.")
    @Column(name = "vehicle_model_name", nullable = false)
    val modelName: String,

    @NotBlank(message = "O tipo do veículo não pode ser vazio.")
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type")
    val vehicleType: VehicleTypeEnum,

    @NotNull(message = "O ano do veículo não pode ser nulo.")
    @Positive(message = "O ano do veículo deve ser um número positivo.")
    @Column(name = "vehicle_year")
    val vehicleYear: Int,

    @NotNull(message = "A quantidade de portas não pode ser nula.")
    @Min(value = 0, message = "A quantidade de portas não pode ser negativa.")
    @Column(name = "door_quantity")
    val doorQuantity: Int,

    @NotBlank(message = "O tipo de combustível não pode ser vazio.")
    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type")
    val fuelType: VehicleFuelTypeEnum,

    @NotBlank(message = "A placa do veículo não pode ser vazia.")
    @Pattern(
        regexp = "[A-Z]{3}[0-9][A-Z][0-9]{2}",
        message = "Formato de placa inválido. Use o padrão Mercosul (ex: ABC1D23)."
    )
    @Column(name = "license_plate", unique = true)
    val licensePlate: String,

    @NotBlank(message = "A cor do veículo não pode ser vazia.")
    @Size(max = 30, message = "A cor deve ter no máximo 30 caracteres.")
    @Column(name = "color")
    val color: String,

    @NotBlank(message = "A quilometragem não pode ser vazia.")
    @Column(name = "mileage")
    val mileage: String,

    @Column(name = "creation_date", updatable = false)
    val creationDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "update_date")
    var updateDate: LocalDateTime? = null,

    @NotBlank(message = "O RENAVAM não pode ser vazio.")
    @field:Size(min = 11, max = 11, message = "O RENAVAM deve conter exatamente 11 dígitos.")
    @Column(name = "renavam", unique = true)
    val renavam: String,

    @field:NotNull(message = "O status do veículo não pode ser nulo.")
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_status", nullable = false)
    var vehicleStatus: VehicleStatusEnum = VehicleStatusEnum.AVAILABLE,

    @Pattern(
        regexp = "\\d{6}-\\d",
        message = "O código FIPE deve seguir o formato XXXXXX-X."
    )
    @Column(name = "fipe_code")
    var fipeCode: String? = null,

    @field:Positive(message = "O valor da diária deve ser positivo.")
    @Column(name = "daily_rate_in_cents", nullable = false)
    var dailyRateInCents: Int,

    @Positive(message = "O valor FIPE deve ser um número positivo.")
    @Column(name = "fipe_value_in_cents")
    var fipeValueInCents: Int? = null,

    @Column(name = "image_url", length = 1000)
    @field:Size(max = 1000, message = "A URL da imagem não pode exceder 255 caracteres.")
    var imageUrl: String? = null,

    @NotBlank(message = "O Chassi não pode ser vazio.")
    @Size(min = 17, max = 17, message = "O Chassi deve conter exatamente 17 caracteres.")
    @Column(name = "vin", unique = true)
    val vin: String
)
