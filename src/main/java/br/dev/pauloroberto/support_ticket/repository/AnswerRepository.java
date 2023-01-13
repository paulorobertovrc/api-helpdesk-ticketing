package br.dev.pauloroberto.support_ticket.repository;

import br.dev.pauloroberto.support_ticket.model.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
