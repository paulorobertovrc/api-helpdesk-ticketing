package br.dev.pauloroberto.support_ticket.domain.repository;

import br.dev.pauloroberto.support_ticket.domain.model.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
