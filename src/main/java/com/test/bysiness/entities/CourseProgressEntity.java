package com.test.bysiness.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COURSE_PROGRESS")
@EqualsAndHashCode(exclude = {"courseProgressId"})
@ToString(exclude = {"courseProgressId"})
@Data
public class CourseProgressEntity {
    @Id
    @Column(name = "COURSE_PROGRESS_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseProgressId;
    @ManyToOne
    @JoinColumn(name = "COURSE_ID")
    @JsonIgnore
    private CourseEntity course;
    @ManyToOne
    @JoinColumn(name = "SUSCRIBER_ID")
    @JsonIgnore
    private UserEntity suscriber;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            mappedBy = "parentProgressCourse", orphanRemoval = true)
    private List<PassedTestEntity> passedTests;

    public void addOption(PassedTestEntity test) {
        passedTests.add(test);
        test.setParentProgressCourse(this);
    }
}
