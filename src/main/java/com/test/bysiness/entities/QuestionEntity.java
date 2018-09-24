package com.test.bysiness.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.bysiness.utilities.QuestionType;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "QUESTIONS")
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"id", "parentTest", "options"})
@Data
public class QuestionEntity {
    @Id
    @Column(name = "QUESTION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "QUESTION", nullable = false)
    private String question;
    @Column(name = "ANSWER_NO_OPTION", nullable = false)
    private String answerForNoOptions;
    @Column(name = "QUESTION_TYPE", nullable = false)
    @Enumerated
    private QuestionType type;

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            mappedBy = "parentQuestion", orphanRemoval = true)
    private Set<OptionEntity> options = new HashSet<>();

    public void addOption(OptionEntity option) {
        options.add(option);
        option.setParentQuestion(this);
    }

    @ManyToOne
    @JoinColumn(name = "TEST_ID")
    @JsonIgnore
    private TestEntity parentTest;
}
