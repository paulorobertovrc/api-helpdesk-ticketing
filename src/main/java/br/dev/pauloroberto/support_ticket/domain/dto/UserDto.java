package br.dev.pauloroberto.support_ticket.domain.dto;

import br.dev.pauloroberto.support_ticket.domain.model.user.User;
import org.jetbrains.annotations.NotNull;

public record UserDto(
        Long id,
        String name,
        String email,
        String roles
) {
    public UserDto(@NotNull User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles().toString()
        );
    }

    public static UserDto from(@NotNull User user) {
        return new UserDto(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRoles().toString()
        );
    }
}
