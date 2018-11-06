package com.test.bysiness.creation.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "TOPIC")
@NoArgsConstructor
@Getter
@Setter
public class TopicEntity {
    @Id
    private Long id;
    @Column(name = "TOPIC_NAME")
    private String description;

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "topic")
    List<CourseEntity> courses;

    public void addCourse(CourseEntity course) {
        courses.add(course);
        course.setTopic(this);
    }
}
