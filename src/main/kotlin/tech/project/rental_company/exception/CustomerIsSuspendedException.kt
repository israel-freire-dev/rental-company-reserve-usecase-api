package tech.project.rental_company.exception

class CustomerIsSuspendedException(
    code: String = "CustomerIsSuspendedException",
    reason: String = "Este cliente está com sua conta suspensa."
): BusinessException(code = code, reason = reason)
