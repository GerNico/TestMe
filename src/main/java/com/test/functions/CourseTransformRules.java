package com.test.functions;

import com.test.bysiness.creation.dto.CourseData;
import com.test.bysiness.creation.dto.TopicData;
import com.test.bysiness.creation.entities.CourseEntity;
import com.test.bysiness.creation.entities.TopicEntity;

import java.util.function.Function;

public class CourseTransformRules {
    private static Function<TopicEntity, TopicData> topicEntityToDto = topicEntity -> {
        TopicData data = new TopicData();
        data.setId(topicEntity.getId());
        data.setName(topicEntity.getName());
        return data;
    };

    public static Function<TopicData, TopicEntity> topicDataToEntity = data -> {
        TopicEntity entity = new TopicEntity();
        entity.setId(data.getId());
        entity.setName(data.getName());
        return entity;
    };

    public static Function<CourseEntity, CourseData> courseEntityToCourse = courseEntity -> {
        CourseData course = new CourseData();
        course.setId(courseEntity.getId());
        course.setCourseName(courseEntity.getCourseName());
        course.setCourseDescription(courseEntity.getCourseDescription());
        course.setPictureUrl(courseEntity.getCourseUrl());
        course.setTopic(topicEntityToDto.apply(courseEntity.getTopic()));
        course.setAuthorId(courseEntity.getAuthor().getId());
        course.setEstimate(courseEntity.getEstimate());
        return course;
    };
}
