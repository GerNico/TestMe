package com.test.repositories;

import com.test.bysiness.entities.AnswerOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerOptionRepository extends JpaRepository<AnswerOptionEntity, Long> {
}
