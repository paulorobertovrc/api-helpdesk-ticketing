package br.dev.pauloroberto.support_ticket.security.service;

import br.dev.pauloroberto.support_ticket.domain.model.user.Authorities;
import br.dev.pauloroberto.support_ticket.domain.model.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TokenService {

    @Value("${support-ticket.jwt.secret}")
    private String secret;
    @Value("${support-ticket.jwt.expiration}")
    private String expiration;

    public String generateToken(User user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Support Ticket API")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withClaim("authorities", user.getAuthorities().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Error generating token");
        }
    }

    public boolean isValid(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWT.require(algorithm)
                    .build()
                    .verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSubject(String token) { // username
        try {
            return JWT.decode(token).getSubject();
        } catch (JWTDecodeException e) {
            throw new RuntimeException("Error decoding token");
        }
    }

    public Long getUserId(String token) { // id
        try {
            return JWT.decode(token).getClaim("id").asLong();
        } catch (JWTDecodeException e) {
            throw new RuntimeException("Error decoding token");
        }
    }

    public List<Authorities> getUserAuthorities(String token) {
        try {
            return JWT.decode(token).getClaim("authorities").asList(Authorities.class);
        } catch (JWTDecodeException e) {
            throw new RuntimeException("Error decoding token");
        }
    }
}
