package com.test.repositories;

import com.test.bysiness.entities.CourseProgressEntity;
import com.test.bysiness.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLogin(String login);

    UserEntity findByEmail(String email);

    @Query("select cp from CourseProgressEntity cp join fetch cp.course where cp.suscriber.id = :id ")
    List<CourseProgressEntity> getUserProgress(@Param(value = "id") Long UserId);

}