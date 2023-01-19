package br.dev.pauloroberto.support_ticket.domain.service;

import br.dev.pauloroberto.support_ticket.domain.dto.NewRoleDto;
import br.dev.pauloroberto.support_ticket.domain.model.user.Role;
import br.dev.pauloroberto.support_ticket.domain.model.user.UserRoles;
import br.dev.pauloroberto.support_ticket.domain.repository.RoleRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void createRole(@RequestBody @Valid @NotNull Role role) {
        roleRepository.save(role);
    }

    public Role fromNewRoleDto(@NotNull NewRoleDto newRoleDto) {
        Role role = new Role();
        role.setUserRoles(UserRoles.valueOf(newRoleDto.role()));

        return role;
    }

    public Role fromString(@NotNull String role) {
        Role newRole = new Role();
        newRole.setUserRoles(UserRoles.fromDescription(role));

        return newRole;
    }

    public Role findRoleByUserRoles(String roleUser) {
        return roleRepository.findByUserRoles(UserRoles.valueOf(roleUser));
    }

    public Role findRoleByUserRoles(UserRoles userRoles) {
        return roleRepository.findByUserRoles(userRoles);
    }
}
