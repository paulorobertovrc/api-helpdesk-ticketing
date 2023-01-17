package br.dev.pauloroberto.support_ticket.domain.model;

import br.dev.pauloroberto.support_ticket.domain.model.ticket.Ticket;
import br.dev.pauloroberto.support_ticket.domain.model.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private boolean solution = false;
}
