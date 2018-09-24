package com.test.bysiness.dto;

import com.test.bysiness.utilities.Roles;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Subscriber {
    private long id;
    private String login;
    private String email;
    private List<Roles> roles;
    private List<Subscription> subscriptions;
    private Set<Long> coursesId;
}