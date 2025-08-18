package tech.project.rental_company.exception

class ImageNotFoundException(
    code: String = "ImageNotFoundException",
    reason: String = "Imagem não foi encontrada no sistema"
): BusinessException(code = code, reason = reason)
