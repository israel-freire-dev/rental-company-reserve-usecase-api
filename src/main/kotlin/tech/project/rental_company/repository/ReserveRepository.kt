package tech.project.rental_company.repository


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.project.rental_company.model.reserve.CustomerSnapshot
import tech.project.rental_company.model.reserve.Reserve
import java.util.Optional


@Repository
interface ReserveRepository : JpaRepository<Reserve, Long> {


    fun findByCustomerData(customerData: CustomerSnapshot): Reserve

    fun findByPublicId(publicId: String): Optional<Reserve>


}