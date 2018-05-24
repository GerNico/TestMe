package com.test.servicies;

import com.test.bysiness.User;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserServiceTest {
    @Autowired
    private UserService userService;
    private static User userToPersist;
    private static User userToFind;

    @BeforeClass
    public static void init() {
        userToPersist = new User();

        userToFind = new User();
        userToFind.setLogin("userToDelete");
        userToFind.setEmail("userToDelete@email.com");
        userToFind.setPasswordHash("SVKANDV-savashifbna-kdmpkpokmkbd");
        userToFind.setRole("admin");
    }

    @Test
    public void saveNewUser() {
        userToPersist.setLogin("userToPersist");
        userToPersist.setEmail("toPersist@email.com");
        userToPersist.setPasswordHash("SVKANDV-sakdfbna-kdmbdmkbd");
        userToPersist.setRole("user");
        assertNull(userToPersist.getId());
        userToPersist = userService.save(userToPersist);
        assertNotNull(userToPersist.getId());

    }

    @Test
    public void getSomeUserTest() {
        userToFind = userService.save(userToFind);
        User found = userService.get(userToFind.getId());
        assertEquals(userToFind, found);
    }

    @After
    @Test
    public void cleanUp() {
        if (userToPersist.getId() != null) userService.delete(userToPersist);
        if (userToFind.getId() != null) userService.delete(userToFind);
        assertNull(userService == null ? userService.get(userToPersist.getId()) : null);
        assertNull(userService == null ? userService.get(userToFind.getId()) : null);
    }

}