package br.dev.pauloroberto.support_ticket.domain.dto;

public record RoleDto(
        String role
) {

    public static RoleDto fromRole(String role) {
        return new RoleDto(role);
    }
}
