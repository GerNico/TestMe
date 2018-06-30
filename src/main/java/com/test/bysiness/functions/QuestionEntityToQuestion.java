package com.test.bysiness.functions;

import com.test.bysiness.dto.Question;
import com.test.bysiness.entities.QuestionEntity;

import java.util.function.Function;
import java.util.stream.Collectors;

public class QuestionEntityToQuestion implements Function<QuestionEntity, Question> {
    @Override
    public Question apply(QuestionEntity questionEntity) {
        return convert(questionEntity);
    }

    public static Question convert(QuestionEntity questionEntity) {
        Question question = new Question();
        question.setId(questionEntity.getId());
        question.setQuestion(questionEntity.getQuestion());
        question.setType(questionEntity.getType());
        question.setAnswerForNoOptions(questionEntity.getAnswerForNoOptions());
        question.setOptions(
                questionEntity.getOptions().stream()
                        .map(OptionEntityToOption::convert)
                        .collect(Collectors.toSet()));

        return question;
    }
}
