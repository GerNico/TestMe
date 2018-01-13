package com.test.bysiness;

public class Topic {
    private int id;
    private String topicName;

    public Topic(int id, String topicName) {
        this.id = id;
        this.topicName = topicName;
    }

    public Integer getId() {
        return id;
    }

    public String getTopicName() {
        return topicName;
    }
}
