package tech.project.rental_company.exception

class ReserveCanceledException(
    code: String = "ReserveCanceledException",
    reason: String = "Esta reserva já está CANCELADA"
): BusinessException(code = code, reason = reason)
