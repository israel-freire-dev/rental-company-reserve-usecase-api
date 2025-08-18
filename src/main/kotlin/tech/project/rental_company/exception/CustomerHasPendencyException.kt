package tech.project.rental_company.exception

class CustomerHasPendencyException (
    code: String = "CustomerHasPendencyException",
    reason: String = "O cliente precisa quitar débito em aberto para efetuar nova reserva."
): BusinessException(code = code, reason = reason)