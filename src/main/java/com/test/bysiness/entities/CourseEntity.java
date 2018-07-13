package com.test.bysiness.entities;

import lombok.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity(name = "COURSES")
@EqualsAndHashCode(exclude = {"id","subscribedUsers"})
@NoArgsConstructor
@Getter
@Setter
public class CourseEntity {
    @Id
    @Column(name = "COURSE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "COURSE_NAME")
    String courseName;
    @Column(name = "COURSE_DESCRIPTION")
    String courseDescription;
    @Column(name = "COURSE_PICT_URL")
    String courseUrl;
    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "parentCourse")
    List<TestEntity> tests = new ArrayList<>();

    public void addTest(TestEntity test) {
        tests.add(test);
        test.setParentCourse(this);
    }
    @ManyToMany(mappedBy = "subscribedCourses")
    Set<UserEntity> subscribedUsers = new HashSet<>();

    public Set<String> getSubscribers(){
        return subscribedUsers.stream().map(UserEntity::getLogin).collect(Collectors.toSet());
    }
}
