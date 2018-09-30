package com.kms.service;

import com.kms.entity.UserBookmark;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserBookmarkService {
    UserBookmark get(Integer id);
    List<UserBookmark> list();
    void add(UserBookmark serBookmark);
    void update(UserBookmark UserBookmark);
    void delete(Integer id);
}
