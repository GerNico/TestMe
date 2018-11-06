package com.test.bysiness.creation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.bysiness.suscribers.entities.UserEntity;
import com.test.bysiness.usage.entities.CourseProgressEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "COURSE")
public class CourseEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "COURSE_NAME")
    private String courseName;
    @Column(name = "COURSE_DESCRIPTION")
    private String courseDescription;
    @Column(name = "COURSE_PICT_URL")
    private String courseUrl;
    @Column(name = "ESTIMATE")
    private Long estimate;

    @ManyToOne
    @JoinColumn(name = "TOPIC_ID")
    @JsonIgnore
    private TopicEntity topic;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    @JsonIgnore
    private UserEntity author;

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "parentCourse")
    private List<TestEntity> tests = new ArrayList<>();

    public void addTest(TestEntity test) {
        tests.add(test);
        test.setParentCourse(this);
    }

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "course")
    List<CourseProgressEntity> courseProgresses = new ArrayList<>();

    public void addCourseProgress(CourseProgressEntity progressOfUser){
        progressOfUser.setBeginDate(LocalDateTime.now());
        progressOfUser.setProcessDate(LocalDateTime.now());
        progressOfUser.setCourse(this);
        courseProgresses.add(progressOfUser);
    }
}


