package tech.project.rental_company.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.project.rental_company.model.reserve.ReserveDTO
import tech.project.rental_company.model.reserve.ReserveRequest
import tech.project.rental_company.service.ReserveService

@Tag(name ="Reservas", description = "Endpoints para gerenciamento de reservas")
@RestController
@RequestMapping("/api/v1")
class ReserveController(
    private val reserveService: ReserveService
) {

    @GetMapping("/reserves")
    @Operation(summary = "Lista todas as reservas no sistema")
    fun getAllReserves(): ResponseEntity<List<ReserveDTO>> {
        return ResponseEntity.ok().body(reserveService.findAllReserves())
    }


    @GetMapping("/reserve/{customerName}")
    @Operation(summary = "Buscar reserva por cliente")
    fun getReserveByCustomerName(
        @PathVariable(required = true) customerName: String
    ): ResponseEntity<ReserveDTO> {
        return ResponseEntity.ok().body(reserveService.findByCustomerName(customerName))
    }


    @PostMapping("/reserve/create")
    @Operation(summary = "Criar reserva no sistema")
    fun createReserve(@RequestBody request: ReserveRequest): ResponseEntity<ReserveDTO> {
        return ResponseEntity.ok().body(reserveService.createReserve(request))
    }


    @PostMapping("/reserve/{reserveId}/cancel")
    @Operation(summary = "Cancelar reserva")
    fun cancelReserve(
        @PathVariable(value = "reserveId", required = true) reserveId: String
    ): ResponseEntity<ReserveDTO>{
        return ResponseEntity.ok().body(reserveService.cancelReserve(reserveId))
    }


    @PostMapping("/reserve/{reserveId}/complete")
    @Operation(summary = "Concluir reserva")
    fun completeReserve(
        @PathVariable(value = "reserveId", required = true) reserveId: String,
    ): ResponseEntity<ReserveDTO> {
        reserveService.completeReserve(reserveId)
        return ResponseEntity.noContent().build()
    }

}