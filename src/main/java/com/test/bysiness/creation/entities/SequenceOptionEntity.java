package com.test.bysiness.creation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "SEQUENCE_BASED_OPTION")
@EqualsAndHashCode(exclude = {"id", "parentQuestion"})
@ToString(exclude = {"id", "parentQuestion"})
@Data
public class SequenceOptionEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "OPTION_TEXT")
    private String text;
    @Column(name = "TOTAL_IN_SEQUENCE")
    private Long totalInSequence;
    @Column(name = "INDEX_IN_SEQUENCE")
    private Long indexInSequence;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    @JsonIgnore
    QuestionEntity parentQuestion;
}
