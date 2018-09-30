package com.kms.mapper;

import com.kms.entity.User;

import java.util.List;

public interface UserMapper {
    int delete(Integer id);

    int add(User record);

    User get(Integer id);

    List<User> list();

    int update(User record);

    int updateByPrimaryKeySelective(User record);

    int insertSelective(User record);
}