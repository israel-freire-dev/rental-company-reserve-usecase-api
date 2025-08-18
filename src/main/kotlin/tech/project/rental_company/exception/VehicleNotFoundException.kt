package tech.project.rental_company.exception

import org.springframework.http.HttpStatus

class VehicleNotFoundException(
    code: String = "VehicleNotFoundException",
    reason: String = "Veículo não encontrado na base de dados do sitema"
): BusinessException(code = code, reason = reason)
