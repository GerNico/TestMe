package com.test.repositories;

import com.test.bysiness.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    public List<QuestionEntity> findAllByParentTestId(Long id);
}