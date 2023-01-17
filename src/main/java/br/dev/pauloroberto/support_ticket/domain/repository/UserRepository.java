package br.dev.pauloroberto.support_ticket.domain.repository;

import br.dev.pauloroberto.support_ticket.domain.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
