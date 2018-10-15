package com.test.bysiness.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ANSWER")
@EqualsAndHashCode(exclude = {"answerId"})
@ToString(exclude = {"answerId"})
@Data
public class AnswerEntity {
    @Id
    @Column(name = "ANSWER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long answerId;
    @ManyToOne
    @JoinColumn(name = "Question_id")
    @JsonIgnore
    private QuestionEntity questionToAnswer;
    @Column(name = "IS_CORRECT")
    private boolean isCorrect;
    @Column(name = "GIVEN_ANSWER")
    private String givenAnswer;
    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            mappedBy = "parentAnswer", orphanRemoval = true)
    private List<AnswerOptionEntity> selectedOptions;

    public void addOption(AnswerOptionEntity option) {
        selectedOptions.add(option);
        option.setParentAnswer(this);
    }

    @ManyToOne
    @JoinColumn(name = "PASSED_TEST_ID")
    @JsonIgnore
    private PassedTestEntity parentPassedTest;
}
