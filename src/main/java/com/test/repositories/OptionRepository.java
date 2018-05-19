package com.test.repositories;

import com.test.bysiness.questions.OptionFromDB;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<OptionFromDB, Integer> {
}
