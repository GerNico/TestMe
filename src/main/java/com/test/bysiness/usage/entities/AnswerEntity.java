package com.test.bysiness.usage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.bysiness.creation.entities.QuestionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ANSWER")
@EqualsAndHashCode(exclude = {"answerId"})
@ToString(exclude = {"answerId"})
@Data
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long answerId;

    @Column(name = "IS_CORRECT")
    private boolean isCorrect;

    @Column(name = "GIVEN_ANSWER")
    private String givenAnswer;

    @Column(name = "IS_JSON")
    private boolean isAnswerJson;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    @JsonIgnore
    private QuestionEntity questionToAnswer;

    @ManyToOne
    @JoinColumn(name = "PASSED_TEST_ID")
    @JsonIgnore
    private PassedTestEntity parentPassedTest;
}
