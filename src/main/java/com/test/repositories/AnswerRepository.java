package com.test.repositories;

import com.test.bysiness.usage.entities.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
}
