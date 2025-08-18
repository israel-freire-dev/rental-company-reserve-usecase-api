package tech.project.rental_company.exception

class ReserveNotFoundException(
    code: String = "ReserveNotFoundException",
    reason: String = "Reserva não encontrada na base de dados do sistema"
): BusinessException(code = code, reason = reason)