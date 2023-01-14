package br.dev.pauloroberto.support_ticket.dto;

import br.dev.pauloroberto.support_ticket.model.ticket.Ticket;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public record TicketDto(
    Long id,
    String title,
    String description,
    String ticketCategory,
    String status,
    UserDto user,
    List<AnswerDto> answers
) {
    public TicketDto(@NotNull Ticket ticket) {
        this(
            ticket.getId(),
            ticket.getTitle(),
            ticket.getDescription(),
            ticket.getTicketCategory().name(),
            ticket.getStatus().name(),
            UserDto.from(ticket.getUser()),
            AnswerDto.from(ticket.getAnswers())
        );
    }

    public TicketDto(@NotNull TicketDto ticketDto) {
        this(
                ticketDto.id(),
                ticketDto.title(),
                ticketDto.description(),
                ticketDto.ticketCategory(),
                ticketDto.status(),
                ticketDto.user(),
                ticketDto.answers()
        );
    }

    public static List<TicketDto> from(@NotNull List<Ticket> tickets) {
        return tickets.stream()
                .map(TicketDto::new)
                .collect(Collectors.toList());
    }
}
