package br.dev.pauloroberto.support_ticket.model.ticket;

import br.dev.pauloroberto.support_ticket.model.Answer;
import br.dev.pauloroberto.support_ticket.model.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TicketCategory ticketCategory;
    private LocalDateTime createdAt = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private TicketStatus status = TicketStatus.OPEN;
    @Enumerated(EnumType.STRING)
    private TicketPriority priority = TicketPriority.NORMAL;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "ticket")
    private List<Answer> answers = new ArrayList<>();
}
