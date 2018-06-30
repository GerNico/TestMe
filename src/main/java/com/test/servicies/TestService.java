package com.test.servicies;

import com.test.bysiness.entities.TestEntity;
import com.test.repositories.TestRepository;
import org.springframework.stereotype.Service;

@Service
public class TestService {

   private TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    TestEntity save (TestEntity test){
        return testRepository.save(test);
    }

    public TestEntity get(Long id){return testRepository.findOne(id);}
}
