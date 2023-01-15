package br.dev.pauloroberto.support_ticket.controller;

import br.dev.pauloroberto.support_ticket.dto.NewTicketDto;
import br.dev.pauloroberto.support_ticket.dto.TicketDto;
import br.dev.pauloroberto.support_ticket.model.ticket.Ticket;
import br.dev.pauloroberto.support_ticket.service.TicketService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<NewTicketDto> newTicket(@RequestBody @Valid NewTicketDto newTicketDto,
                                                  @NotNull UriComponentsBuilder uriBuilder) {
        Ticket ticket = ticketService.fromDto(newTicketDto);
        ticketService.createTicket(ticket);

        Long newTicketId = ticket.getId();

        URI uri = uriBuilder.path("/tickets/{id}").buildAndExpand(ticket.getId()).toUri();

        return ResponseEntity.created(uri).body(new NewTicketDto(
                newTicketId,
                newTicketDto.title(),
                newTicketDto.description(),
                newTicketDto.category()
        ));
    }

    @GetMapping()
    public ResponseEntity<List<TicketDto>> listTickets() {
        List<Ticket> tickets = ticketService.listTickets();
        return ResponseEntity.ok(TicketDto.from(tickets));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicket(@PathVariable Long id) {
        TicketDto ticketDto = ticketService.getTicket(id);
        return ResponseEntity.ok(ticketDto);
    }
}
