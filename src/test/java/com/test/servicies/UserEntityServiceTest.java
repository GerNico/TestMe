package com.test.servicies;

import com.test.bysiness.entities.UserEntity;
import com.test.bysiness.utilities.Roles;
import com.test.servicies.impl.UserServiceImpl;
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
public class UserEntityServiceTest {
    @Autowired
    private UserServiceImpl userServiceImpl;
    private static UserEntity userEntityToPersist;
    private static UserEntity userEntityToFind;

    @BeforeClass
    public static void init() {
        userEntityToPersist = new UserEntity();

        userEntityToFind = new UserEntity();
        userEntityToFind.setLogin("userToDelete");
        userEntityToFind.setEmail("userToDelete@email.com");
        userEntityToFind.setPasswordHash("SVKANDV-savashifbna-kdmpkpokmkbd");
        userEntityToFind.setRole(Roles.Admin);
    }

    @Test
    public void saveNewUser() {
        userEntityToPersist.setLogin("userEntityToPersist");
        userEntityToPersist.setEmail("toPersist@email.com");
        userEntityToPersist.setPasswordHash("SVKANDV-sakdfbna-kdmbdmkbd");
        userEntityToPersist.setRole(Roles.User);
        assertNull(userEntityToPersist.getId());
        userEntityToPersist = userServiceImpl.save(userEntityToPersist);
        assertNotNull(userEntityToPersist.getId());

    }

    @Test
    public void getSomeUserTest() {
        userEntityToFind = userServiceImpl.save(userEntityToFind);
        UserEntity found = userServiceImpl.get(userEntityToFind.getId());
        assertEquals(userEntityToFind, found);
    }

    @After
    @Test
    public void cleanUp() {
        if (userEntityToPersist.getId() != null) userServiceImpl.delete(userEntityToPersist);
        if (userEntityToFind.getId() != null) userServiceImpl.delete(userEntityToFind);
        assertNull(userServiceImpl == null ? userServiceImpl.get(userEntityToPersist.getId()) : null);
        assertNull(userServiceImpl == null ? userServiceImpl.get(userEntityToFind.getId()) : null);
    }

}