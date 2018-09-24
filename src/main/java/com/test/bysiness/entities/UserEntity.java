package com.test.bysiness.entities;

import com.test.bysiness.utilities.Roles;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@EqualsAndHashCode(exclude = {"id","subscribedCourses"})
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "LOGIN", unique = true, nullable = false)
    private String login;
    @Column(name = "EMAIL", unique = true, nullable = false)
    @Email
    private String email;
    @Column(name = "USER_ROLE", nullable = false)
    @Enumerated
    private Roles role;
    @Column(name = "USER_PASSWORD", nullable = false)
    private String passwordHash;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "SUBSCRIPTIONS",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    Set<CourseEntity> subscribedCourses = new HashSet<>();

    public void subscribe(CourseEntity course) {
        subscribedCourses.add(course);
        course.getSubscribedUsers().add(this);
    }
}
