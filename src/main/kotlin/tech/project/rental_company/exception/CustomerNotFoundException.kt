package tech.project.rental_company.exception


class CustomerNotFoundException(
    code: String = "CustomerNotFoundException",
    reason: String = "Cliente não está cadastrado no sistema"
): BusinessException(code = code, reason = reason)