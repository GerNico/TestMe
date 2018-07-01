package com.test.bysiness.functions;

import com.test.bysiness.dto.Question;
import com.test.bysiness.entities.QuestionEntity;

import java.util.function.Function;
import java.util.stream.Collectors;

public class QuestionTransformRules {

    public static Function<QuestionEntity, Question> questionEntityToQuestion = questionEntity -> {
        Question question = new Question();
        question.setId(questionEntity.getId());
        question.setQuestion(questionEntity.getQuestion());
        question.setType(questionEntity.getType());
        question.setAnswerForNoOptions(questionEntity.getAnswerForNoOptions());
        question.setOptions(
                questionEntity.getOptions().stream()
                        .map(OptionTransformRules.optionEntityToOption)
                        .collect(Collectors.toSet()));

        return question;
    };

    public static Function<Question, QuestionEntity> questionToQuestionEntity = question -> {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(question.getId());
        questionEntity.setQuestion(question.getQuestion());
        questionEntity.setType(question.getType());
        questionEntity.setAnswerForNoOptions(question.getAnswerForNoOptions());
        question.getOptions().stream()
                .map(OptionTransformRules.optionToOptionEntity)
                .forEach(questionEntity::addOption);
        return questionEntity;
    };

}
