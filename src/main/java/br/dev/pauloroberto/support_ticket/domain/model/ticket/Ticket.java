package br.dev.pauloroberto.support_ticket.domain.model.ticket;

import br.dev.pauloroberto.support_ticket.domain.model.Answer;
import br.dev.pauloroberto.support_ticket.domain.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

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
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private LocalDateTime closedAt;
    private LocalDateTime deletedAt;
    private Long closedBy;
    private Long deletedBy;
    private boolean deleted = false;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketCategory category;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status = TicketStatus.OPEN;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketPriority priority = TicketPriority.NORMAL;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER)
    private List<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
    }

    public void closeTicket(Long closedBy) {
        this.status = TicketStatus.CLOSED;
        this.closedAt = LocalDateTime.now();
        this.closedBy = closedBy;
    }

    public void deleteTicket(Long deletedBy) {
        this.status = TicketStatus.DELETED;
        this.deletedAt = LocalDateTime.now();
        this.deletedBy = deletedBy;
        this.deleted = true;
    }

    public void updateTicket(@NotNull Ticket ticket) {
        this.title = ticket.getTitle();
        this.description = ticket.getDescription();
        this.category = ticket.getCategory();
        this.priority = ticket.getPriority();
        this.updatedAt = LocalDateTime.now();
    }
}
