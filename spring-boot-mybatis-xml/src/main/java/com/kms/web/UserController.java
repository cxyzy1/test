package com.kms.web;

import java.util.List;

import com.kms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kms.entity.User;
import com.kms.mapper.UserMapper;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> list() {
        List<User> users = userService.list();
        return users;
    }

    @RequestMapping("/getUser")
    public User getUser(Integer id) {
        User user = userService.get(id);
        return user;
    }

    @RequestMapping("/add")
    public void add(User user) {
        userService.add(user);
    }

    @RequestMapping(value="update")
    public void update(User user) {
        userService.update(user);
    }

    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
    }

}