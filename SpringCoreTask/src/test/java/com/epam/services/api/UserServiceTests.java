package com.epam.services.api;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.sc.beans.User;
import com.epam.sc.services.api.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:context.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTests {

    @Autowired
    UserService userService;
    
    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setName("Test1");
        Assert.assertTrue(userService.registerUser(user) > 0);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setName("Test2"); 
        Long userId = userService.registerUser(user);  
        User savedUser = userService.getUserById(userId);   
        Assert.assertNotNull(savedUser);
        Assert.assertEquals("Test2", savedUser.getName());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setName("Test User New");       
        Long userId = userService.registerUser(user);       
        user = userService.getUserById(userId);
        user.setName("Test User Updated");       
        userService.updateUser(user);       
        user = userService.getUserById(userId);      
        Assert.assertEquals("Test User Updated", user.getName());
    }
}
