package com.test.repositories;

import com.test.bysiness.entities.PassedTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassedTestRepository extends JpaRepository<PassedTestEntity, Long> {
}
