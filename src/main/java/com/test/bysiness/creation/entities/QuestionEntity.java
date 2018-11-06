package com.test.bysiness.creation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "QUESTION")
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"id", "parentTest", "options"})
@Data
public class QuestionEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "QUESTION", nullable = false)
    private String question;
    @Column(name = "score")
    private Long score;

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            mappedBy = "parentQuestion", orphanRemoval = true)
    private Set<OptionEntity> options = new HashSet<>();

    public void addOption(OptionEntity option) {
        option.setParentQuestion(this);
        options.add(option);
    }

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            mappedBy = "parentQuestion", orphanRemoval = true)
    private List<SequenceOptionEntity> sequenceOptions = new ArrayList<>();

    public void addSequenceOption(SequenceOptionEntity option) {
        option.setParentQuestion(this);
        sequenceOptions.add(option);
    }

    @ManyToOne
    @JoinColumn(name = "TEST_ID")
    @JsonIgnore
    private TestEntity parentTest;
}
