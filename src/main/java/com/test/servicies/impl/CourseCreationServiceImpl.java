package com.test.servicies.impl;

import com.test.bysiness.creation.dto.CourseData;
import com.test.bysiness.creation.dto.QuestionData;
import com.test.bysiness.creation.dto.TestData;
import com.test.bysiness.creation.entities.CourseEntity;
import com.test.bysiness.creation.entities.TopicEntity;
import com.test.bysiness.suscribers.dto.SubscriberData;
import com.test.repositories.CourseRepository;
import com.test.repositories.TopicRepository;
import com.test.repositories.UserRepository;
import com.test.servicies.CourseCreationService;
import com.test.servicies.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.test.functions.CourseTransformRules.courseEntityToCourse;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseCreationServiceImpl implements CourseCreationService {
    private final CourseRepository courseRepository;
    private final SubscriberService subscriberService;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final String defaultUrlPicture = "";

    @Override
    public Optional<CourseData> createNewCourse(CourseData baseCourseData) {
        CourseEntity newCourseEntity = new CourseEntity();
        SubscriberData currentUser = subscriberService.getCurrentUser();
        newCourseEntity.setAuthor(userRepository.findByLogin(currentUser.getLogin()));
        newCourseEntity.setCourseName(baseCourseData.getCourseName());
        newCourseEntity.setCourseDescription(baseCourseData.getCourseDescription());
        TopicEntity topic = topicRepository.findOne(baseCourseData.getTopic().getId());
        newCourseEntity.setTopic(topic);
        newCourseEntity.setCourseUrl(defaultUrlPicture);
        newCourseEntity.setEstimate(baseCourseData.getEstimate());
        newCourseEntity = courseRepository.save(newCourseEntity);
        return Optional.ofNullable(courseEntityToCourse.apply(newCourseEntity));
    }


    @Override
    public Optional<TestData> createNewTest(TestData test, Long parentCourse) {
        CourseEntity parent = courseRepository.findOne(parentCourse);
        if (parent == null) return Optional.empty();
        //Todo
        return Optional.empty();
    }

    @Override
    public Optional<QuestionData> createNewQuestion(QuestionData question, Long parentTestId) {
        return Optional.empty();
        //Todo
    }
}
