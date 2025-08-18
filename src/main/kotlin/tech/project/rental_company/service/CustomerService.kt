package tech.project.rental_company.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tech.project.rental_company.exception.CustomerNotFoundException
import tech.project.rental_company.model.customer.Customer
import tech.project.rental_company.model.customer.CustomerDTO
import tech.project.rental_company.repository.CustomerRepository


@Service
class CustomerService(
   private val customerRepository: CustomerRepository
) {

    fun findCustomerByName(customerName: String): Customer {
        val customer = customerRepository.findByNameIgnoreCase(customerName)
            .orElseThrow {
                CustomerNotFoundException()
            }
        return customer
    }

    fun findCustomerByDocument(document: String): CustomerDTO {
        val customer = customerRepository.findByDocument(document)
            .orElseThrow {
                CustomerNotFoundException()
            }
        return CustomerDTO.build(customer)
    }

    @Transactional
    fun updateCustomerPendencyStatus(publicId: String, hasPendency: Boolean): CustomerDTO {
        val customer = customerRepository.findByPublicId(publicId)
            .orElseThrow { EntityNotFoundException("Cliente com ID '$publicId' não encontrado.") }

        val updatedCustomer = customer.copy(
            hasPendency = hasPendency
        )

        val savedCustomer = customerRepository.save(updatedCustomer)

        return CustomerDTO.build(savedCustomer)
    }
}