package com.test.bysiness.entities;

import com.test.bysiness.utilities.ValidateString;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@EqualsAndHashCode(exclude = {"id","subscribedCourses"})
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "email", unique = true, nullable = false)
    @Email
    private String email;
    @Column(name = "USER_ROLE", nullable = false)
    @ValidateString(acceptedValues = {"User", "Admin", "Author", "Moderator"}, message = "Invalid role")
    private String role;
    @Column(name = "user_password", nullable = false)
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
