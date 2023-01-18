package br.dev.pauloroberto.support_ticket.domain.model.user;

import br.dev.pauloroberto.support_ticket.domain.model.Answer;
import br.dev.pauloroberto.support_ticket.domain.model.ticket.Ticket;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Email
    private String email;
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Ticket> tickets = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Answer> answers = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authorities> authorities = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
