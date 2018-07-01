package com.test.servicies;

import com.test.bysiness.dto.Course;
import com.test.bysiness.dto.Subscriber;
import com.test.bysiness.entities.CourseEntity;
import com.test.bysiness.functions.CourseTransformRules;
import com.test.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseEntity save(CourseEntity course) {
        return courseRepository.save(course);
    }


    public Course createNewCourse(Course course) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseName(course.getCourseName());
        courseEntity.setCourseDescription(course.getCourseDescription());
        courseEntity.setSubscribedUsers(new HashSet<>());
        courseRepository.save(courseEntity);
        return CourseTransformRules.optionEntityToOption.apply(courseEntity);
    }

    public Course getCourseDTO(Long id){
        CourseEntity courseEntity=courseRepository.findOne(id);
        if (courseEntity==null)return null;
        return CourseTransformRules.optionEntityToOption.apply(courseEntity);
    }

    public void delete(Long id) {
        courseRepository.delete(id);
    }

    public CourseEntity get(Long id) {
        return courseRepository.findOne(id);
    }
}
