package br.dev.pauloroberto.support_ticket.dto;

import br.dev.pauloroberto.support_ticket.model.user.User;

public record UserDto() {
    public static UserDto from(User user) {
        return null;
    }
}
