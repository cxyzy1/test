package com.kms.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kms.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testAdd() {
        int originalCount = userService.list().size();
        add("yin1", "123456");
        add("yin2", "123456");
        Assert.assertEquals(originalCount + 2, userService.list().size());
    }

    @Test
    public void testList() {
        add("yin", "123456");
        List<User> users = userService.list();
        Assert.assertFalse(users.isEmpty());
    }

    @Test
    public void testUpdate() {
        add("yin", "123456");
        final String name = "kms123";
        User user = userService.list().get(0);
        System.out.println(user.toString());
        user.setName(name);
        userService.update(user);
        Assert.assertTrue((name.equals(userService.get(user.getId()).getName())));
    }

    @Test
    public void testDelete() {
        System.out.println( Thread.currentThread().getId());
        add("yin", "123456");
        User user = userService.list().get(0);
        userService.delete(user.getId());
        Assert.assertNull(userService.get(user.getId()));
    }

    private void add(String name, String password) {
        userService.add(new User(name,password));
    }

}