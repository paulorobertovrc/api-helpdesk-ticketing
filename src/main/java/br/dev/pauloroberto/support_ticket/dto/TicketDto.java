package br.dev.pauloroberto.support_ticket.dto;

import br.dev.pauloroberto.support_ticket.model.ticket.Ticket;
import br.dev.pauloroberto.support_ticket.model.ticket.TicketCategory;
import br.dev.pauloroberto.support_ticket.model.ticket.TicketPriority;
import br.dev.pauloroberto.support_ticket.model.ticket.TicketStatus;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record TicketDto(
    Long id,
    String title,
    String description,
    LocalDateTime createdAt,
    TicketCategory category,
    TicketStatus status,
    TicketPriority priority,
    UserDto user,
    List<AnswerDto> answers
) {

    public TicketDto(@NotNull Ticket ticket) {
        this(
            ticket.getId(),
            ticket.getTitle(),
            ticket.getDescription(),
            ticket.getCreatedAt(),
            ticket.getCategory(),
            ticket.getStatus(),
            ticket.getPriority(),
            UserDto.from(ticket.getUser()),
            new ArrayList<>()
        );
        this.answers.addAll(ticket.getAnswers().stream().map(AnswerDto::new).toList());
    }

    public static List<TicketDto> from(@NotNull List<Ticket> tickets) {
        return tickets.stream()
                .map(TicketDto::new)
                .collect(Collectors.toList());
    }
}
