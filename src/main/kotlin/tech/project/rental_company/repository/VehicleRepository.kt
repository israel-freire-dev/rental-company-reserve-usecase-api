package tech.project.rental_company.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.project.rental_company.model.vehicle.Vehicle
import java.util.Optional

@Repository
interface VehicleRepository: JpaRepository<Vehicle, Long> {

    fun findByPublicId(publicId: String): Optional<Vehicle>

    fun findByLicensePlate(licensePlate: String): Optional<Vehicle>
}