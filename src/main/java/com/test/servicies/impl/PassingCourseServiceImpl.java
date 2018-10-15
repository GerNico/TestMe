package com.test.servicies.impl;

import com.test.repositories.UserRepository;
import com.test.servicies.PassingCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassingCourseServiceImpl implements PassingCourseService {
    private final UserRepository userRepository;


}
