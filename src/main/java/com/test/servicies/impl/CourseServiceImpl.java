package com.test.servicies.impl;

import com.test.bysiness.dto.CourseData;
import com.test.bysiness.dto.CourseInfo;
import com.test.bysiness.entities.CourseEntity;
import com.test.bysiness.entities.UserEntity;
import com.test.bysiness.functions.CourseTransformRules;
import com.test.repositories.CourseRepository;
import com.test.servicies.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseEntity save(CourseEntity course) {
        return courseRepository.save(course);
    }


    public CourseData createNewCourse(CourseData course) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseName(course.getCourseName());
        courseEntity.setCourseDescription(course.getCourseDescription());
        courseEntity.setSubscribedUsers(new HashSet<>());
        courseRepository.save(courseEntity);
        return CourseTransformRules.courseEntityToCourse.apply(courseEntity);
    }

    public CourseData getCourseDTO(Long id) {
        CourseEntity courseEntity = courseRepository.findOne(id);
        if (courseEntity == null) return null;
        return CourseTransformRules.courseEntityToCourse.apply(courseEntity);
    }

    public void delete(Long id) {
        courseRepository.delete(id);
    }

    public Stream<CourseInfo> getAllCoursesInfo() {
        return courseRepository.findAll().stream()
                .map(CourseTransformRules.courseEntityToCourseInfo);
    }

    public CourseEntity get(Long id) {
        return courseRepository.findOne(id);
    }

    @Override
    public boolean unsuscribe(UserEntity user) {

        return false;
    }
}
