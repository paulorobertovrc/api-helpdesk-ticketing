package br.dev.pauloroberto.support_ticket.controller;

import br.dev.pauloroberto.support_ticket.domain.dto.NewRoleDto;
import br.dev.pauloroberto.support_ticket.domain.model.user.Role;
import br.dev.pauloroberto.support_ticket.domain.service.RoleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<NewRoleDto> newRole(@RequestBody @Valid NewRoleDto newRoleDto,
                                              @NotNull UriComponentsBuilder uriBuilder) {
        Role role = roleService.fromNewRoleDto(newRoleDto);
        roleService.createRole(role);

        URI uri = uriBuilder.path("/roles/{id}").buildAndExpand(role.getId()).toUri();

        return ResponseEntity.created(uri).body(new NewRoleDto(role.getAuthority()));
    }
}
