package com.test.bysiness;

import java.util.List;

public interface Course {

    List<String> getAuthors();

    List<Test> getAllTests();

    Test getTestById(Integer id);

    Test getTestByName(String name);

}
