package com.test.bysiness.functions;

import com.test.bysiness.dto.AnswerData;
import com.test.bysiness.dto.QuestionData;
import com.test.bysiness.entities.AnswerEntity;
import com.test.bysiness.entities.AnswerOptionEntity;
import com.test.bysiness.entities.QuestionEntity;

import java.util.function.Function;
import java.util.stream.Collectors;

public class QuestionTransformRules {

    public static Function<QuestionEntity, QuestionData> questionEntityToQuestion = questionEntity -> {
        QuestionData question = new QuestionData();
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

    public static Function<QuestionData, QuestionEntity> questionToQuestionEntity = question -> {
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

    public static Function<AnswerEntity, AnswerData> answerEntityToAnswer = answerEntity -> {
        AnswerData answerData = new AnswerData();
        answerData.setAnswerId(answerEntity.getAnswerId());
        answerData.setQuestionId(answerEntity.getQuestionToAnswer().getId());
        answerData.setCorrect(answerEntity.isCorrect());
        answerData.setGivenAnswer(answerEntity.getGivenAnswer());
        answerData.setSelectedOptions(answerEntity.getSelectedOptions()
                .stream().map(AnswerOptionEntity::getId).collect(Collectors.toList()));
        return answerData;
    };

    public static Function<AnswerData, AnswerEntity> answerToEmptyAnswerEntity = answerData -> {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAnswerId(answerData.getAnswerId());
        answerEntity.setCorrect(answerData.isCorrect());
        answerEntity.setGivenAnswer(answerData.getGivenAnswer());
        return answerEntity;
    };
}
