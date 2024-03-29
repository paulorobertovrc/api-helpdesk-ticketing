package br.dev.pauloroberto.support_ticket.domain.service;

import br.dev.pauloroberto.support_ticket.domain.dto.NewUserDto;
import br.dev.pauloroberto.support_ticket.domain.model.user.User;
import br.dev.pauloroberto.support_ticket.domain.repository.UserRepository;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public void newUser(@RequestBody @Valid @NotNull User user) {
        userRepository.save(user);
    }

    public User fromNewUserDto(@NotNull NewUserDto newUserDto) {
        User user = new User();
        user.setName(newUserDto.name());
        user.setEmail(newUserDto.email());
        user.setPassword(User.encodePassword(newUserDto.password()));
        user.setRoles(
                new ArrayList<>() {{
                    add(roleService.findRoleByUserRoles("ROLE_USER"));
                }}
        );

        System.out.println("User: " + user);

        return user;
    }

    public Optional<UserDetails> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByEmail(username));
    }
}
