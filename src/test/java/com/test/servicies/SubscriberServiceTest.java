package com.test.servicies;

import com.test.repositories.CourseRepository;
import com.test.repositories.TestRepository;
import com.test.repositories.UserRepository;
import com.test.servicies.impl.SubscriberServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class SubscriberServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private TestRepository testRepository;
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private SubscriberServiceImpl subscriberService;

    @Test
    public void uu() {
        assertTrue(true);
    }

}