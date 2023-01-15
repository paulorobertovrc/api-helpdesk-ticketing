package br.dev.pauloroberto.support_ticket.dto;

import br.dev.pauloroberto.support_ticket.model.ticket.TicketCategory;
import br.dev.pauloroberto.support_ticket.utils.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;

public record NewTicketDto(
    Long id,
    @NotBlank
    String title,
    @NotBlank
    String description,
    @NotBlank @ValueOfEnum(enumClass = TicketCategory.class)
    String category
) {
    public NewTicketDto(String title, String description, String category) {
        this(null, title, description, category);
        // the id is null because it is a new ticket
        // this same dto will be used to return the ticket, but with the id from the database
        // the id
    }
}
