package com.test.bysiness.questions;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "OPTION_OF_ANSWER")
@EqualsAndHashCode(exclude = {"id"})
@ToString(exclude = "id")
@NoArgsConstructor
@Getter
@Setter
public class OptionFromDB {
    @Id
    @Column(name = "OPTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "OPTION_TEXT")
    private String text;
    @Column(name = "IS_CORRECT", columnDefinition = "boolean default false")
    private Boolean isCorrect;
    @Column(name = "IS_SEQUENCE_BASED", columnDefinition = "boolean default false")
    private Boolean isSequenceBased;
    @Column(name = "NUMBERS_IN_SEQUENCE", columnDefinition = "int default 0")
    private Integer numberInSequence;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    QuestionFromDB parentQuestion;
}
