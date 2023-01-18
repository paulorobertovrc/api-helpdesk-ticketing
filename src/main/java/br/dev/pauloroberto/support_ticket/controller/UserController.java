package br.dev.pauloroberto.support_ticket.controller;

import br.dev.pauloroberto.support_ticket.domain.dto.NewUserDto;
import br.dev.pauloroberto.support_ticket.domain.dto.UserDto;
import br.dev.pauloroberto.support_ticket.domain.model.user.User;
import br.dev.pauloroberto.support_ticket.domain.service.UserService;
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
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserDto> newUser(@RequestBody @Valid NewUserDto newUserDto,
                                           @NotNull UriComponentsBuilder uriBuilder) {
        User user = userService.fromNewUserDto(newUserDto);
        userService.newUser(user);

        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserDto(user));
    }
}
