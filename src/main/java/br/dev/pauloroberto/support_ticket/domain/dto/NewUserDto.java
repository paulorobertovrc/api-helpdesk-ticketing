package br.dev.pauloroberto.support_ticket.domain.dto;

import br.dev.pauloroberto.support_ticket.domain.model.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record NewUserDto(
    @NotBlank
    String name,
    @Email @NotBlank
    String email,
    @NotBlank String password
) {
    public User toUser(NewUserDto newUserDto) {
        User user = new User();
        user.setName(newUserDto.name());
        user.setEmail(newUserDto.email());
        user.setPassword(User.encodePassword(newUserDto.password()));

        return user;
    }
}
