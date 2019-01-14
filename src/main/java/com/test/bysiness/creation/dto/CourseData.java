package com.test.bysiness.creation.dto;

import lombok.Data;

@Data
public class CourseData {
    private long id;
    private String courseName;
    private String pictureUrl;
    private String courseDescription;
    private TopicData topic;
    private long authorId;
    private long estimate;
}
