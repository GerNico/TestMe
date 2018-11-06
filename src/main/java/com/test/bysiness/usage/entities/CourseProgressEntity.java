package com.test.bysiness.usage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.bysiness.creation.entities.CourseEntity;
import com.test.bysiness.suscribers.entities.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "COURSE_PROGRESS")
@EqualsAndHashCode(exclude = {"courseProgressId"})
@ToString(exclude = {"courseProgressId"})
@Data
public class CourseProgressEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseProgressId;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID")
    @JsonIgnore
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "SUSCRIBER_ID")
    @JsonIgnore
    private UserEntity subscriber;

    @Column(name = "BEGIN_DATE", nullable = false)
    private LocalDateTime beginDate;
    @Column(name = "PROGRESS_DATE", nullable = false)
    private LocalDateTime processDate;

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            mappedBy = "courseProgress", orphanRemoval = true)
    private List<PassedTestEntity> passedTests;

    public void addPassedTest(PassedTestEntity test) {
        passedTests.add(test);
        test.setCourseProgress(this);
    }
}
