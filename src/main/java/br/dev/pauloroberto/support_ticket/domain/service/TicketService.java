package br.dev.pauloroberto.support_ticket.domain.service;

import br.dev.pauloroberto.support_ticket.domain.dto.NewTicketDto;
import br.dev.pauloroberto.support_ticket.domain.dto.TicketDto;
import br.dev.pauloroberto.support_ticket.domain.dto.UpdateTicketDto;
import br.dev.pauloroberto.support_ticket.domain.model.ticket.Ticket;
import br.dev.pauloroberto.support_ticket.domain.model.ticket.TicketCategory;
import br.dev.pauloroberto.support_ticket.domain.repository.TicketRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void createTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public List<Ticket> listTickets() {
        return (List<Ticket>) ticketRepository.findAll();
    }

    public Ticket fromNewTicketDto(NewTicketDto newTicketDto) {
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(newTicketDto, ticket);
        ticket.setCategory(TicketCategory.fromDescription(newTicketDto.category()));

        return ticket;
    }

    public TicketDto toDto(Ticket ticket) {
        return new TicketDto(ticket);
    }

    public TicketDto getTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        return toDto(ticket);
    }

    public Ticket fromUpdateTicketDto(UpdateTicketDto updateTicketDto) {
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(updateTicketDto, ticket);

        return ticket;
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow();
    }

    public TicketDto updateTicket(Long id, UpdateTicketDto updateTicketDto) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();

        if (!updateTicketDto.title().isBlank()
                && !updateTicketDto.title().equals(ticket.getTitle())
        ) {
            ticket.setTitle(updateTicketDto.title());
        }

        if (!updateTicketDto.description().isBlank()
                && !updateTicketDto.description().equals(ticket.getDescription())
        ) {
            ticket.setDescription(updateTicketDto.description());
        }

        if (!updateTicketDto.category().isBlank()
                && !updateTicketDto.category().equals(ticket.getCategory().getDescription())
        ) {
            ticket.setCategory(TicketCategory.fromDescription(updateTicketDto.category()));
        }

        ticketRepository.save(ticket);

        return toDto(ticket);
    }

    public void deleteTicket(Long id, Long userId) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        ticket.deleteTicket(userId);
        ticketRepository.save(ticket);
    }
}
