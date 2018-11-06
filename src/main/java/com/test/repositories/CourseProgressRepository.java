package com.test.repositories;

import com.test.bysiness.usage.entities.CourseProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseProgressRepository extends JpaRepository<CourseProgressEntity, Long> {

    void deleteAllBySubscriberId(Long suscriberId);
}
