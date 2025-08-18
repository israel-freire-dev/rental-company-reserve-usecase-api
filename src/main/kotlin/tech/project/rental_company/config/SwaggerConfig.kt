package tech.project.rental_company.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {

    @Bean
    fun apiInfo(): OpenAPI? {
        val apiName  = "Rental Company API"
        return OpenAPI()
            .info(
                Info()
                    .title(apiName)
                    .description("API responsável pelo caso de uso RESERVA do projeto Locadora")
                    .contact(Contact().name("Israel e Silas"))
            )
    }
}