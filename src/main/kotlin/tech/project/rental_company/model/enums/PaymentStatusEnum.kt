package tech.project.rental_company.model.enums

enum class PaymentStatusEnum {
    PENDING,    // Pagamento pendente (reserva criada, mas não paga)
    PAID,       // Pago com sucesso
    CANCELED,   // Pagamento foi cancelado antes da conclusão
    REFUNDED    // Reembolsado (em caso de cancelamento da reserva após o pagamento)
}