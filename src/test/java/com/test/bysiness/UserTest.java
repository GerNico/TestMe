package com.test.bysiness;

import com.test.servicies.UserService;
import org.junit.*;
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
public class UserTest {
    @Autowired
    private UserService userService;
    private static User userToDelete;
    private static User newUser;
    private static User userToFind;
    private static User userToUpdate;
    private static User userUpdated;

    @BeforeClass
    public static void init() {
        userToDelete = new User("userToDelete", "userToDelete@email.com", "SVKANDV-sakdfbna-kdmbdmkbd", null);
        newUser = new User("newUser", "newUser@email.com", "fhjfj-vjhh-dfgdgfd-8vjhvjh", null);
        userToFind = new User("userToFind", "toFind@email.com", "sdhfjkj-snhln-gmsmkdfm-sgnl", null);
        userToUpdate = new User("userToUpdate", "toUpdate@email.com", "jdbnand-adbk-anjbsjkjs-ajhjfh", null);
        userUpdated = new User("userUpdated", "updated@email.com", "jdd-adk-ankjs-ajhjfh", null);
    }

    @Test
    public void saveNewUser() {
        User userToPersist = new User("newUser", "newUser@email.com", "fhjfj-vjhh-dfgdgfd-8vjhvjh", null);
        User persistedUser = userService.save(userToPersist);
        assertNotNull(persistedUser);
        assertEquals(userToPersist, persistedUser);
        userService.delete(userToPersist);
    }

    @Test
    public void findUser() {
        userService.save(userToFind);
        User foundUser = userService.get(userToFind.getId());
        assertEquals(userToFind, foundUser);
        userService.delete(userToFind);
    }

    @Test
    public void updateUser() {
        userService.save(userToUpdate);
        User before = userService.get(userToUpdate.getId());
        assertEquals(userToUpdate, before);
        userUpdated.setId(userToUpdate.getId());
        userUpdated = userService.save(userUpdated);
        User after = userService.get(userToUpdate.getId());
        assertEquals(userUpdated, after);
        userService.delete(userUpdated);
    }

    @Test
    public void deleteUser() {
        User before=userService.save(userToDelete);
        assertNotNull(before);
        userService.delete(userToDelete);
        User foundUser = userService.get(before.getId());
        assertNull(foundUser);
    }
}