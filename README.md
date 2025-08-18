# 🚗 API para Registro de Reserva de Veículos

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.25-7F52FF?style=for-the-badge&logo=kotlin)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.4-6DB33F?style=for-the-badge&logo=spring-boot)
![JPA](https://img.shields.io/badge/JPA-Hibernate-59666C?style=for-the-badge&logo=hibernate)
![Gradle](https://img.shields.io/badge/Gradle-8.x-02303A?style=for-the-badge&logo=gradle)

## 📄 Sobre o Projeto

Este projeto é uma API RESTful desenvolvida como parte de um trabalho acadêmico, com o objetivo de implementar o caso de uso **"Registrar Reserva de Veículo"** de um sistema de locadora de carros.

A aplicação foi construída com Kotlin e Spring Boot, seguindo princípios de arquitetura limpa, com clara separação de responsabilidades, validação de regras de negócio, tratamento de exceções centralizado e documentação interativa via Swagger.

## ✨ Caso de Uso: Registrar Reserva

O foco principal do projeto é a implementação do fluxo de criação de uma nova reserva, garantindo a integridade e a consistência dos dados através de uma série de validações de negócio.

### Fluxo Principal
1.  **Requisição:** O sistema recebe uma requisição (`POST /api/v1/reserves`) contendo os dados do cliente, do veículo desejado e o período da locação (data de início e fim).
2.  **Validação de Negócio:** Antes de prosseguir, o sistema executa uma cadeia de validações críticas:
    * **Cliente:** Verifica se o cliente existe e se seu status é válido (não está **suspenso** e não possui **pendências financeiras**).
    * **Veículo:** Verifica se o veículo solicitado existe e se seu status está **`DISPONÍVEL`** para locação.
    * **Datas:** Valida se a data de devolução é posterior à data de locação.
3.  **Cálculo de Valor:** O valor total da reserva é calculado no backend, multiplicando o número de dias da locação pela diária cadastrada para o veículo.
4.  **Persistência (Transacional):** Se todas as validações forem aprovadas:
    * Uma nova `Reserva` é salva no banco de dados com o status inicial de `ATIVA`.
    * O status do `Veículo` correspondente é atualizado para `RESERVADO`, garantindo que ele não possa ser alugado por outra pessoa.
5.  **Resposta:** A API retorna um status `200 OK` com os detalhes completos da reserva recém-criada.


## 🛠️ Tecnologias Utilizadas

* **Linguagem:** [Kotlin](https://kotlinlang.org/) 1.9.25
* **Framework Principal:** [Spring Boot](https://spring.io/projects/spring-boot) 3.5.4
* **Persistência de Dados:** [Spring Data JPA](https://spring.io/projects/spring-data-jpa) com [Hibernate](https://hibernate.org/)
* **Banco de Dados:** [H2 Database](https://www.h2database.com/html/main.html) (para desenvolvimento)
* **Migrações de Banco:** [Flyway](https://flywaydb.org/)
* **Validações:** [Jakarta Bean Validation](https://beanvalidation.org/)
* **Documentação da API:** [Springdoc OpenAPI (Swagger UI)](https://springdoc.org/)
* **Build Tool:** [Gradle](https://gradle.org/) com Kotlin DSL (`build.gradle.kts`)
* **Ambiente de Execução:** Java 21

## 🚀 Como Rodar o Projeto

### Pré-requisitos
* [JDK 21](https://www.oracle.com/java/technologies/downloads/#java21) ou superior.

### Passos para Execução
1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/israel-freire-dev/rental-company-reserve-usecase-api.git
    ```
2.  **Execute a aplicação via Gradle Wrapper:**
    ```bash
    # No Windows
    .\gradlew bootRun

    # No Linux ou macOS
    ./gradlew bootRun
    ```
    A API estará rodando em `http://localhost:7772`.

## 🗺️ Uso da API e Documentação

A API está documentada com o Swagger UI. Após iniciar a aplicação, acesse a documentação interativa para visualizar e testar os endpoints.

* **URL do Swagger UI:** [http://localhost:7772/swagger-ui.html](http://localhost:7772/swagger-ui.html)

Para visualizar o banco de dados em memória:
* **URL do H2 Console:** [http://localhost:7772/h2-console](http://localhost:7772/h2-console)
* **JDBC URL:** `jdbc:h2:mem:db-rental-company`
* **User Name:** `admin`
* **Password:** `admin`

