package br.dev.pauloroberto.support_ticket.model.user;

import br.dev.pauloroberto.support_ticket.model.Answer;
import br.dev.pauloroberto.support_ticket.model.ticket.Ticket;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Email
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Answer> answers = new ArrayList<>();
    private List<UserRoles> roles = new ArrayList<>(
            List.of(UserRoles.USER)
    );
}
