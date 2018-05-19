package com.test.repositories;

import com.test.bysiness.questions.QuestionFromDB;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<QuestionFromDB, Integer> {

}