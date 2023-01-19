package br.dev.pauloroberto.support_ticket.domain.repository;

import br.dev.pauloroberto.support_ticket.domain.model.user.Role;
import br.dev.pauloroberto.support_ticket.domain.model.user.UserRoles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByUserRoles(UserRoles userRoles);
}
