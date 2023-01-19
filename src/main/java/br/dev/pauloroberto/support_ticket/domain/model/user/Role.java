package br.dev.pauloroberto.support_ticket.domain.model.user;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private UserRoles userRoles;

    @Override
    public String getAuthority() {
        return this.userRoles.toString();
    }
}
