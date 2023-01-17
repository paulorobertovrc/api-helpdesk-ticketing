package br.dev.pauloroberto.support_ticket.security.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record AuthenticationDto(
    String username,
    String password
) {
    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
