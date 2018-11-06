package com.test.bysiness.usage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.bysiness.creation.entities.TestEntity;
import com.test.utilities.PassingStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PASSED_TEST")
@EqualsAndHashCode(exclude = {"id"})
@ToString(exclude = {"id"})
@Data
public class PassedTestEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PASSING_DATE")
    private LocalDateTime passingDate;

    @Column(name = "Passing_mode")
    private String lastChangeDate;

   @Column(name = "PASSING_STATUS")
   @Enumerated
   private PassingStatus passingStatus;

   @Column(name = "PASSING_SCORE")
   private Long score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEST_ID")
    @JsonIgnore
    private TestEntity basedTest;

    @ManyToOne
    @JoinColumn(name = "COURSE_PROGRESS_ID")
    @JsonIgnore
    private CourseProgressEntity courseProgress;

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            mappedBy = "parentPassedTest", orphanRemoval = true)
    private List<AnswerEntity> ansers;

    public void addAnswer(AnswerEntity answer) {
        ansers.add(answer);
        answer.setParentPassedTest(this);
    }

}
