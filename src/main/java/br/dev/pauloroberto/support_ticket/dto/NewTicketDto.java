package br.dev.pauloroberto.support_ticket.dto;

import br.dev.pauloroberto.support_ticket.model.ticket.Ticket;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public record NewTicketDto(
    Long id,
    String title,
    String description,
    String ticketCategory
) {
    public NewTicketDto(@NotNull String title, @NotNull String description, @NotNull String ticketCategory) {
        this(null, title, description, ticketCategory);
    }

    public NewTicketDto(Ticket ticket) {
        this(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getTicketCategory().name()
        );
    }

    public static List<NewTicketDto> from(@NotNull List<Ticket> tickets) {
        return tickets.stream()
                .map(NewTicketDto::new)
                .collect(Collectors.toList());
    }
}
