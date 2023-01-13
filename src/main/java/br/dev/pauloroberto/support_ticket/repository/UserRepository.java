package br.dev.pauloroberto.support_ticket.repository;

import br.dev.pauloroberto.support_ticket.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
