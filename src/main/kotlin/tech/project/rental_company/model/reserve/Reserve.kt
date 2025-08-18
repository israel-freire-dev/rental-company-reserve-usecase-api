package tech.project.rental_company.model.reserve


import java.time.LocalDate
import java.util.UUID
import jakarta.persistence.*
import jakarta.validation.Valid
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import tech.project.rental_company.model.enums.PaymentStatusEnum
import tech.project.rental_company.model.enums.ReservationStatusEnum
import java.time.LocalDateTime

@Entity
@Table(schema = "rentalcompany", name = "tb_reserve")
data class Reserve(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "public_id", unique = true, updatable = false, nullable = false, length = 36)
    val publicId: String = UUID.randomUUID().toString(),

    @Valid
    @Embedded
    val customerData: CustomerSnapshot,

    @field:Valid
    @Embedded
    val vehicleData: VehicleSnapshot,

    @NotNull(message = "O valor não pode ser nulo.")
    @Positive(message = "O valor da reserva deve ser positivo.")
    @Column(name = "amount_in_cents", nullable = false)
    val amountInCents: Long,

    @NotNull(message = "A data de locação é obrigatória.")
    @FutureOrPresent(message = "A data de locação não pode ser no passado.")
    @Column(name = "rental_date", nullable = false)
    val rentalDate: LocalDate,

    @NotNull(message = "A data de devolução é obrigatória.")
    @Future(message = "A data de devolução deve ser uma data futura.")
    @Column(name = "return_date", nullable = false)
    val returnDate: LocalDate,

    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_status", nullable = false)
    var reservationStatus: ReservationStatusEnum,

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    var paymentStatus: PaymentStatusEnum = PaymentStatusEnum.PENDING,

    @Column(name = "creation_date", nullable = false, updatable = false)
    val creationDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "update_date")
    var updateDate: LocalDateTime? = null
)
