package tech.project.rental_company.exception

class ReserveRentalDateException(
    code: String = "ReserveRentalDateException",
    reason: String = "A data de devolução não pode ser anterior à data de locação"
): BusinessException(code = code, reason = reason)