package com.test.bysiness.creation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "OPTION")
@EqualsAndHashCode(exclude = {"id", "parentQuestion"})
@ToString(exclude = {"id", "parentQuestion"})
@Data
public class OptionEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "OPTION_TEXT")
    private String text;
    @Column(name = "IS_CORRECT")
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    @JsonIgnore
    QuestionEntity parentQuestion;
}
