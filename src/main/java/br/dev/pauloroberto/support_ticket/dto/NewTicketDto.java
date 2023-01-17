package br.dev.pauloroberto.support_ticket.dto;

import br.dev.pauloroberto.support_ticket.model.ticket.TicketCategory;
import br.dev.pauloroberto.support_ticket.utils.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;

public record NewTicketDto(
    @NotBlank
    String title,
    @NotBlank
    String description,
    @NotBlank @ValueOfEnum(enumClass = TicketCategory.class)
    String category
) {
}
