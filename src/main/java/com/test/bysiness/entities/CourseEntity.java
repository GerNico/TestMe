package com.test.bysiness.entities;

import com.test.bysiness.utilities.Clone;
import lombok.*;

import javax.persistence.*;
import java.util.*;

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

    public Set<UserEntity> getSubscribers(){
        return (Set<UserEntity>) Clone.deepCopy(subscribedUsers);
    }
}
