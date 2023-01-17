package br.dev.pauloroberto.support_ticket.controller;

import br.dev.pauloroberto.support_ticket.domain.model.user.User;
import br.dev.pauloroberto.support_ticket.security.dto.AuthenticationDto;
import br.dev.pauloroberto.support_ticket.security.dto.TokenDto;
import br.dev.pauloroberto.support_ticket.security.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthorizationController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthorizationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid AuthenticationDto authenticationDto) {
        UsernamePasswordAuthenticationToken authenticationToken = authenticationDto.toAuthenticationToken();

        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            String token = tokenService.generateToken((User) authentication.getPrincipal());

            return ResponseEntity.ok(new TokenDto(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
