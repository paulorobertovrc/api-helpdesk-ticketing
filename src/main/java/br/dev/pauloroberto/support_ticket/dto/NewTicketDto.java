package br.dev.pauloroberto.support_ticket.dto;

import br.dev.pauloroberto.support_ticket.model.ticket.Ticket;
import br.dev.pauloroberto.support_ticket.model.ticket.TicketCategory;
import br.dev.pauloroberto.support_ticket.utils.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public record NewTicketDto(
    Long id,
    @NotBlank
    String title,
    @NotBlank
    String description,
    @NotBlank @ValueOfEnum(enumClass = TicketCategory.class)
    String ticketCategory
) {
    public NewTicketDto(String title, String description, String ticketCategory) {
        this(null, title, description, ticketCategory);

        // the id is null because it is a new ticket
        // this same dto will be used to return the ticket, but with the id from the database
        // the id
    }

    public NewTicketDto(@NotNull Ticket ticket) {
        // This constructor is used by the static method List<TicketDto> from(List<Ticket> tickets)
        this(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getTicketCategory().toString()
        );
    }

    public static List<NewTicketDto> from(@NotNull List<Ticket> tickets) {
        return tickets.stream()
                .map(NewTicketDto::new)
                .collect(Collectors.toList());
    }
}
