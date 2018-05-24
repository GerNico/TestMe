package com.test.bysiness.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "OPTION_OF_ANSWER")
@EqualsAndHashCode(exclude = {"id","parentQuestion"})
@ToString(exclude = {"id","parentQuestion"})
@NoArgsConstructor
@Getter
@Setter
public class OptionEntity {
    @Id
    @Column(name = "OPTION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "OPTION_TEXT")
    private String text;
    @Column(name = "IS_CORRECT")
    private Boolean isCorrect=false;
    @Column(name = "IS_SEQUENCE_BASED")
    private Boolean isSequenceBased=false;
    @Column(name = "NUMBERS_IN_SEQUENCE")
    private Long numberInSequence=0L;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    QuestionEntity parentQuestion;
}
