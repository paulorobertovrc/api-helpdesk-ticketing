package br.dev.pauloroberto.support_ticket.repository;

import br.dev.pauloroberto.support_ticket.model.ticket.Ticket;
import br.dev.pauloroberto.support_ticket.model.ticket.TicketStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findByUserId(Long userId);
    List<Ticket> findByUserName(String userName);
    List<Ticket> findByStatus(TicketStatus status);
}
