package br.dev.pauloroberto.support_ticket.repository;

import br.dev.pauloroberto.support_ticket.model.ticket.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
