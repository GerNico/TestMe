package com.test.repositories;

import com.test.bysiness.creation.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}