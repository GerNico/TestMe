package com.test.servicies;

import com.test.bysiness.entities.CourseEntity;
import com.test.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    public CourseService() {
    }

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseEntity save(CourseEntity course){
       return courseRepository.save(course);
    }

    public void delete(Long id) {
        courseRepository.delete(id);
    }

    public CourseEntity get(Long id){
        return courseRepository.getOne(id);
    }
}
