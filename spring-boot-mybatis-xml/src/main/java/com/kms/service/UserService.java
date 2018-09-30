package com.kms.service;

import com.kms.entity.User;

import java.util.List;

public interface UserService {
    User get(Integer id);
    List<User> list();
    void add(User user);
    void update(User user);
    void delete(Integer id);
}
