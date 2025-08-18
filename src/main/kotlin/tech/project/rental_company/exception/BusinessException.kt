package tech.project.rental_company.exception

import org.springframework.http.HttpStatus

open class BusinessException(
    val code: String,
    val reason: String = " "
) : RuntimeException()
