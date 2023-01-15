package br.dev.pauloroberto.support_ticket.service;

import br.dev.pauloroberto.support_ticket.dto.NewTicketDto;
import br.dev.pauloroberto.support_ticket.dto.TicketDto;
import br.dev.pauloroberto.support_ticket.model.ticket.Ticket;
import br.dev.pauloroberto.support_ticket.model.ticket.TicketCategory;
import br.dev.pauloroberto.support_ticket.repository.TicketRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    public final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void createTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public List<Ticket> listTickets() {
        return (List<Ticket>) ticketRepository.findAll();
    }

    public Ticket fromDto(NewTicketDto newTicketDto) {
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(newTicketDto, ticket);
        ticket.setTicketCategory(TicketCategory.fromDescription(newTicketDto.ticketCategory()));
        return ticket;
    }

    public TicketDto toDto(Ticket ticket) {
        return new TicketDto(ticket);
    }

    public TicketDto getTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        return toDto(ticket);
    }
}
