package tech.project.rental_company.exception

class VehicleReservedException(
    code: String = "VehicleReservedException",
    reason: String = "O veículo já está reservado."
): BusinessException(code = code, reason = reason)