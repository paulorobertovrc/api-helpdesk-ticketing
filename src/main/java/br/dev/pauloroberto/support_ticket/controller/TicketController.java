package br.dev.pauloroberto.support_ticket.controller;

import br.dev.pauloroberto.support_ticket.domain.dto.NewTicketDto;
import br.dev.pauloroberto.support_ticket.domain.dto.TicketDto;
import br.dev.pauloroberto.support_ticket.domain.dto.UpdateTicketDto;
import br.dev.pauloroberto.support_ticket.domain.model.ticket.Ticket;
import br.dev.pauloroberto.support_ticket.domain.service.TicketService;
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

    @PostMapping
    @Transactional
    public ResponseEntity<TicketDto> newTicket(@RequestBody @Valid NewTicketDto newTicketDto,
                                               @NotNull UriComponentsBuilder uriBuilder) {
        Ticket ticket = ticketService.fromNewTicketDto(newTicketDto);
        ticketService.createTicket(ticket);

        URI uri = uriBuilder.path("/tickets/{id}").buildAndExpand(ticket.getId()).toUri();

        return ResponseEntity.created(uri).body(new TicketDto(ticket));
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

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Long id, @RequestBody @Valid UpdateTicketDto updateTicketDto) {
        return ResponseEntity.ok(ticketService.updateTicket(id, updateTicketDto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteTicket(@PathVariable Long id, @RequestBody Long userId) {
        ticketService.deleteTicket(id, userId);
        return ResponseEntity.noContent().build();
    }
}
