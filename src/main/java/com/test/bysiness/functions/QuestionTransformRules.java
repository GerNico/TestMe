package com.test.bysiness.functions;

import com.test.bysiness.dto.Answer;
import com.test.bysiness.dto.Question;
import com.test.bysiness.entities.AnswerEntity;
import com.test.bysiness.entities.AnswerOptionEntity;
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

    public static Function<AnswerEntity, Answer> answerEntityToAnswer = answerEntity -> {
        Answer answer = new Answer();
        answer.setAnswerId(answerEntity.getAnswerId());
        answer.setQuestionId(answerEntity.getQuestionToAnswer().getId());
        answer.setCorrect(answerEntity.isCorrect());
        answer.setGivenAnswer(answerEntity.getGivenAnswer());
        answer.setSelectedOptions(answerEntity.getSelectedOptions()
                .stream().map(AnswerOptionEntity::getId).collect(Collectors.toList()));
        return answer;
    };

    public static Function<Answer, AnswerEntity> answerToEmptyAnswerEntity = answer -> {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAnswerId(answer.getAnswerId());
        answerEntity.setCorrect(answer.isCorrect());
        answerEntity.setGivenAnswer(answer.getGivenAnswer());
        return answerEntity;
    };
}
