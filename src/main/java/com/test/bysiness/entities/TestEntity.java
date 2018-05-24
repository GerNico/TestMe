package com.test.bysiness.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TESTS")
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"id", "parentCourse", "entities"})
@NoArgsConstructor
@Getter
@Setter
public class TestEntity {
    @Id
    @Column(name = "TEST_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "TEST_DESCRIPTION")
    private String testDescription;
    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, mappedBy = "parentTest")
    private List<QuestionEntity> questions = new ArrayList<>();

    public void addQuestion(QuestionEntity question) {
        questions.add(question);
        question.setParentTest(this);

    }

    @ManyToOne
    @JoinColumn(name = "COURSE_ID")
    private CourseEntity parentCourse;
}
