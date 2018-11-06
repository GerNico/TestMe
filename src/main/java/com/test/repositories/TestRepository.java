package com.test.repositories;

import com.test.bysiness.creation.entities.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
}