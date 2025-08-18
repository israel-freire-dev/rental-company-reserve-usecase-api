package tech.project.rental_company.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.project.rental_company.model.enums.VehicleStatusEnum
import tech.project.rental_company.model.vehicle.VehicleDTO
import tech.project.rental_company.service.VehicleService

@RestController
@RequestMapping("/api/v1/vehicles")
@Tag(name = "Veículos", description = "Endpoints para gerenciamento de veículos")
class VehicleController(
    private val vehicleService: VehicleService
) {

    @GetMapping
    @Operation(summary = "Lista todos os veículos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de veículos retornada com sucesso")
    fun findAllVehicles(): ResponseEntity<List<VehicleDTO>> {
        val vehicles = vehicleService.findAll()
        return ResponseEntity.ok(vehicles)
    }

    @GetMapping("/plate/{licensePlate}")
    @Operation(summary = "Busca um veículo específico pela placa")
    @ApiResponse(responseCode = "200", description = "Veículo encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Veículo com a placa informada não encontrado")
    fun findByLicensePlate(
        @Parameter(description = "Placa do veículo a ser buscado (ex: RST1A23)")
        @PathVariable licensePlate: String
    ): ResponseEntity<VehicleDTO> {
        val vehicle = vehicleService.findVehicleByLicensePlate(licensePlate)
        return ResponseEntity.ok(vehicle)
    }

    @PostMapping("/{licensePlate}/status")
    @Operation(summary = "Atualiza o status de um veículo")
    @ApiResponse(responseCode = "200", description = "Status do veículo atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    @ApiResponse(responseCode = "400", description = "Requisição inválida (ex: status nulo)")
    fun updateVehicleStatus(
        @PathVariable licensePlate: String,
        @PathVariable newStatus: VehicleStatusEnum
    ): ResponseEntity<VehicleDTO> {
        val updatedVehicle = vehicleService.updateVehicleStatus(licensePlate, newStatus)
        return ResponseEntity.ok(updatedVehicle)
    }
}