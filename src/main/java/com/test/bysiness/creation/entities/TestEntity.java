package com.test.bysiness.creation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TEST")
@EqualsAndHashCode(exclude ={ "id","parentCourse"})
@ToString(exclude = {"id", "parentCourse"})
@Getter
@Setter
public class TestEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "TEST_DESCRIPTION")
    private String testDescription;
    @Column(name = "ORDER_INDEX")
    private Long orderIndex;

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "parentTest")
    private List<QuestionEntity> questions = new ArrayList<>();

    public void addQuestion(QuestionEntity question) {
        questions.add(question);
        question.setParentTest(this);
    }

    @ManyToOne
    @JoinColumn(name = "COURSE_ID")
    @JsonIgnore
    private CourseEntity parentCourse;
}
