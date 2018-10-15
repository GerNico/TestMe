package com.test.bysiness.dto;

import com.test.bysiness.utilities.Roles;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class SubscriberData {
    private long id;
    private String login;
    private String email;
    private List<Roles> roles;
    private Set<Long> coursesId;
}