package br.dev.pauloroberto.support_ticket.dto;

import br.dev.pauloroberto.support_ticket.model.Answer;

import java.util.List;

public record AnswerDto() {
    public static List<AnswerDto> from(List<Answer> answers) {
        return null;
    }
}
