package tech.project.rental_company.model.customer

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

@Embeddable
data class Contact(
    @NotBlank(message = "O e-mail não pode ser vazio.")
    @Email(message = "O formato do e-mail é inválido.")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres.")
    @Column(name = "email", unique = true, nullable = false)
    val email: String,

    @NotBlank(message = "O telefone não pode ser vazio.")
    @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres.")
    @Column(name = "phone", nullable = false)
    val phone: String
)

@Embeddable
data class Address(
    @NotBlank(message = "A rua não pode ser vazia.")
    @Size(max = 150, message = "A rua deve ter no máximo 150 caracteres.")
    @Column(name = "street", nullable = false)
    val street: String,

    @NotBlank(message = "O número não pode ser vazio.")
    @Size(max = 10, message = "O número deve ter no máximo 10 caracteres.")
    @Column(name = "number", nullable = false)
    val number: String,

    @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres.")
    @Column(name = "complement")
    val complement: String? = null,

    @NotBlank(message = "O bairro não pode ser vazio.")
    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres.")
    @Column(name = "neighborhood", nullable = false)
    val neighborhood: String,

    @NotBlank(message = "A cidade não pode ser vazia.")
    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres.")
    @Column(name = "city", nullable = false)
    val city: String,

    @NotBlank(message = "O estado não pode ser vazio.")
    @Size(min = 2, max = 2, message = "O estado deve ser a sigla de 2 letras.")
    @Column(name = "state", nullable = false, length = 2)
    val state: String,

    @NotBlank(message = "O CEP não pode ser vazio.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Formato de CEP inválido. Use XXXXX-XXX.")
    @Column(name = "zip_code", nullable = false, length = 9)
    val zipCode: String
)

data class CustomerDTO(
    val customerId: String,
    val name: String,
    val document: String,
    val email: String,
    val phone: String,
    val isSuspended: Boolean,
    val hasPendency: Boolean
) {
    companion object {
        fun build(customer: Customer): CustomerDTO {
            return CustomerDTO(
                customerId = customer.publicId,
                name = customer.name,
                document = customer.document,
                email = customer.contact.email,
                phone = customer.contact.phone,
                isSuspended = customer.isSuspended,
                hasPendency = customer.hasPendency
            )
        }
    }
}

