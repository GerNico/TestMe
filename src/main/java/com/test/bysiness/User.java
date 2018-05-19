package com.test.bysiness;

import com.test.bysiness.utilities.ValidateString;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@EqualsAndHashCode(exclude = {"id", "courses"})
@ToString(exclude = "id")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "email", unique = true, nullable = false)
    @Email
    private String email;
    @Column(name = "USER_ROLE", nullable = false)
    @ValidateString(acceptedValues={"user", "admin","author", "moderator"}, message="Invalid user role")
    private String role;
    @Column(name = "user_password", nullable = false)
    private String passwordHash;
    @Transient
    private List<Course> courses;
}
