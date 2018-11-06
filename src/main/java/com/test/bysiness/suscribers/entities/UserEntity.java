package com.test.bysiness.suscribers.entities;

import com.test.bysiness.creation.entities.CourseEntity;
import com.test.bysiness.usage.entities.CourseProgressEntity;
import com.test.utilities.Roles;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER")
@EqualsAndHashCode(exclude = {"id", "subscribedCourses"})
@Getter
@Setter
public class UserEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "EMAIL")
    @Email
    private String email;
    @Column(name = "USER_ROLE")
    @Enumerated
    private Roles role;
    @Column(name = "PASSWORD")
    private String passwordHash;

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "subscriber")
    private Set<CourseProgressEntity> subscribedCourses = new HashSet<>();

    public void subscribeCourse(CourseEntity course){
        CourseProgressEntity progress=new CourseProgressEntity();
        progress.setSubscriber(this);
        course.addCourseProgress(progress);
        subscribedCourses.add(progress);
    }

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "author")
    private List<CourseEntity> myCourses = new ArrayList<>();

    public void createCourseAsAuthor(CourseEntity course){
        course.setAuthor(this);
        myCourses.add(course);
    }
}
