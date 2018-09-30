package com.kms.service.impl;

import com.kms.entity.User;
import com.kms.mapper.UserMapper;
import com.kms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User get(Integer id) {
        User user = userMapper.get(id);
        return user;
    }

    @Override
    public List<User> list() {
        List<User> users = userMapper.list();
        return users;
    }

    @Override
    public void add(User user) {
        user.setPassword("123456");
        userMapper.add(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }
}
