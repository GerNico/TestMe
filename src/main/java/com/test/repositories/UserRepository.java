package com.test.repositories;

import com.test.bysiness.suscribers.entities.UserEntity;
import com.test.bysiness.usage.entities.CourseProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLogin(String login);

    UserEntity findByEmail(String email);

    @Query(value = "select ue from UserEntity ue join fetch ue.subscribedCourses where ue.id = :userId")
    Set<CourseProgressEntity> findUsersCourseProgresses(@Param(value = "userId")Long userId);

//    @Query(value = "select pte from PassedTestEntity pte where (pte.basedTest.id= :testId) and (pte.courseProgress.courseProgressId in (select ue.subscribedCourses from UserEntity ue))")
//    PassedTestEntity findByUserAndTest(@Param(value = "testId")Long testId, @Param(value = "userId")Long userId);

}