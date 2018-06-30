package com.test.bysiness.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class Subscriber {
    private long id;
    private String login;
    private String email;
    private Set<Long> coursesId;
}