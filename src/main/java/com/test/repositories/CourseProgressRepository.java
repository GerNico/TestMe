package com.test.repositories;

import com.test.bysiness.entities.CourseProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseProgressRepository extends JpaRepository<CourseProgressEntity, Long> {

    void deleteAllBySuscriberId(Long suscriberId);
}
