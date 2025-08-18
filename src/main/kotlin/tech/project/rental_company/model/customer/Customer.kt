package tech.project.rental_company.model.customer

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.UUID

@Entity
@Table(schema = "rentalcompany", name = "tb_customer")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "public_id", unique = true, updatable = false, nullable = false, length = 36)
    val publicId: String = UUID.randomUUID().toString(),

    @NotBlank(message = "O nome não pode ser vazio.")
    @Size(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres.")
    @Column(name = "name", nullable = false)
    val name: String,

    // Para CPF/CNPJ, considere usar uma biblioteca de validação específica (ex: hibernate-validator-brazil)
    @NotBlank(message = "O documento não pode ser vazio.")
    @Size(min = 11, max = 14, message = "O documento deve ter 11 (CPF) ou 14 (CNPJ) dígitos.")
    @Column(name = "document", unique = true, nullable = false)
    val document: String,

    @Valid
    @Embedded
    val contact: Contact,

    @Valid
    @Embedded
    val address: Address,

    @NotNull(message = "O status de suspensão não pode ser nulo.")
    @Column(name = "is_suspended", nullable = false)
    val isSuspended: Boolean = false,

    @NotNull(message = "O status de pendência não pode ser nulo.")
    @Column(name = "has_pendency", nullable = false)
    val hasPendency: Boolean = false
)