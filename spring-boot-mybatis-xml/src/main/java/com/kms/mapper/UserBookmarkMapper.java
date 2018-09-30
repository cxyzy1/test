package com.kms.mapper;

import com.kms.entity.UserBookmark;

import java.util.List;

public interface UserBookmarkMapper {
    int delete(Integer id);

    int add(UserBookmark record);

    List<UserBookmark> list();

    UserBookmark get(Integer id);

    int update(UserBookmark record);

    int updateByPrimaryKey(UserBookmark record);
}