package br.dev.pauloroberto.support_ticket.domain.dto;

import br.dev.pauloroberto.support_ticket.domain.model.user.Authorities;
import br.dev.pauloroberto.support_ticket.domain.model.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record UserDto(
        Long id,
        String name,
        String email,
        List<Authorities> authorities
) {
    public UserDto(@NotNull User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                (List<Authorities>) user.getAuthorities()
        );
    }

    public static UserDto from(@NotNull User user) {
        return new UserDto(
            user.getId(),
            user.getName(),
            user.getEmail(),
            (List<Authorities>) user.getAuthorities()
        );
    }
}
