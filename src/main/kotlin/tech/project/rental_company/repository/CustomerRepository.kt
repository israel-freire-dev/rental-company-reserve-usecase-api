package tech.project.rental_company.repository


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.project.rental_company.model.customer.Customer
import java.util.Optional

@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {

    fun findByPublicId(publicId: String): Optional<Customer>

    fun findByNameIgnoreCase(name: String): Optional<Customer>

    fun findByDocument(document: String): Optional<Customer>
}
