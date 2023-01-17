package br.dev.pauloroberto.support_ticket.dto;

import br.dev.pauloroberto.support_ticket.model.ticket.TicketCategory;
import br.dev.pauloroberto.support_ticket.utils.ValueOfEnum;
import org.springframework.web.bind.annotation.RequestParam;

public record UpdateTicketDto(
    @RequestParam(required = false)
    String title,
    @RequestParam(required = false)
    String description,
    @RequestParam(required = false)
    @ValueOfEnum(enumClass = TicketCategory.class)
    String category
) {
}
