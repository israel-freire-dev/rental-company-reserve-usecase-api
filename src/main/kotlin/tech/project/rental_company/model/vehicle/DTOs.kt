package tech.project.rental_company.model.vehicle

import tech.project.rental_company.model.enums.VehicleFuelTypeEnum
import tech.project.rental_company.model.enums.VehicleTypeEnum


data class VehicleDTO(
    val vehicleId: String,
    val modelName: String,
    val type: VehicleTypeEnum,
    val year: Int,
    val doors: Int,
    val fuel: VehicleFuelTypeEnum,
    val licensePlate: String,
    val color: String,
    val mileage: String,
    var vehicleStatus: String,
    var dailyRateInCents: Int,
    var fipeValueInCents: String,
    val imageUrl: String?
) {
    companion object {
        fun build(vehicle: Vehicle): VehicleDTO {
            return VehicleDTO(
                vehicleId = vehicle.publicId,
                modelName = vehicle.modelName,
                type = vehicle.vehicleType,
                year = vehicle.vehicleYear,
                doors = vehicle.doorQuantity,
                fuel = vehicle.fuelType,
                licensePlate = vehicle.licensePlate,
                color = vehicle.color,
                mileage = vehicle.mileage,
                vehicleStatus = vehicle.vehicleStatus.name,
                dailyRateInCents = vehicle.dailyRateInCents,
                fipeValueInCents = vehicle.fipeValueInCents.toString(),
                imageUrl = vehicle.imageUrl
            )
        }
    }
}