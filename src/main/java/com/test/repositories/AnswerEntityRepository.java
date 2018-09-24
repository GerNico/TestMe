package com.test.repositories;

import com.test.bysiness.entities.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerEntityRepository extends JpaRepository<AnswerEntity, Long> {
}
