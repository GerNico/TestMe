package com.test.bysiness.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "OPTION_OF_ANSWER")
@EqualsAndHashCode(exclude = {"id", "parentQuestion"})
@ToString(exclude = {"id", "parentQuestion"})
@Data
public class OptionEntity {
    @Id
    @Column(name = "OPTION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "OPTION_TEXT")
    private String text;
    @Column(name = "IS_CORRECT")
    private boolean isCorrect = false;
    @Column(name = "IS_SEQUENCE_BASED")
    private boolean isSequenceBased = false;
    @Column(name = "NUMBERS_IN_SEQUENCE")
    private Long numberInSequence = 0L;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    @JsonIgnore
    QuestionEntity parentQuestion;
}
