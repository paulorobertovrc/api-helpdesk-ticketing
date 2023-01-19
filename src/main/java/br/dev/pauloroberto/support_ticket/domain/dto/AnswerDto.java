package br.dev.pauloroberto.support_ticket.domain.dto;

import br.dev.pauloroberto.support_ticket.domain.model.Answer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.List;

public record AnswerDto(
        Long id,
        String description,
        LocalDate createdAt,
        Long userId,
        String userName
) {

    public AnswerDto(Answer answer) {
        this(
                answer.getId(),
                answer.getDescription(),
                answer.getCreatedAt().toLocalDate(),
                answer.getUser().getId(),
                answer.getUser().getName()
        );
    }

    @Contract(pure = true)
    public static @Nullable List<AnswerDto> from(List<Answer> answers) {
        return null;
    }
}
