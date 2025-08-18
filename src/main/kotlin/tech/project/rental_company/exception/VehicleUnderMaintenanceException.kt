package tech.project.rental_company.exception

class VehicleUnderMaintenanceException(
    code: String = "VehicleUnderMaintenanceException",
    reason: String = "O veículo está em manutenção."
): BusinessException(code = code, reason = reason)