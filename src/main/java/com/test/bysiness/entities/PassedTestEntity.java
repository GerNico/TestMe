package com.test.bysiness.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
   @Column(name = "PASSED_TEST_ID")
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
           mappedBy = "parentPassedTest", orphanRemoval = true)
   private List<AnswerEntity> ansers;

    public void addOption(AnswerEntity answer) {
        ansers.add(answer);
        answer.setParentPassedTest(this);
    }

   @Column(name = "START_DATE")
   private LocalDateTime startDate;
   @Column(name = "LAST_CHANGE_DATE")
   private LocalDateTime lastChangeDate;
    @ManyToOne
    @JoinColumn(name = "COURSE_PROGRESS_ID")
    @JsonIgnore
    private CourseProgressEntity courseProgress;
}
