package com.test.repositories;

import com.test.bysiness.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}