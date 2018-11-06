package com.test.repositories;

import com.test.bysiness.creation.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    List<QuestionEntity> findAllByParentTestId(Long id);
}