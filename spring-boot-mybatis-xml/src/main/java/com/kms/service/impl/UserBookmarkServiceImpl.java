package com.kms.service.impl;

import com.kms.entity.UserBookmark;
import com.kms.mapper.UserBookmarkMapper;
import com.kms.service.UserBookmarkService;
import com.kms.utils.MyUid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBookmarkServiceImpl implements UserBookmarkService {
    @Autowired
    private UserBookmarkMapper userBookmarkMapper;

    @Override
    public List<UserBookmark> list() {
        List<UserBookmark> userBookmarks = userBookmarkMapper.list();
        return userBookmarks;
    }

    @Override
    public UserBookmark get(Integer id) {
        UserBookmark userBookmark = userBookmarkMapper.get(id);
        return userBookmark;
    }

    @Override
    public void add(UserBookmark userBookmark) {
        userBookmark.setBookmarkId(MyUid.getUid());
        userBookmark.setBookmarkUrl("http://www.baidu.com");
        userBookmarkMapper.add(userBookmark);
    }

    @Override
    public void update(UserBookmark userBookmark) {
        userBookmarkMapper.update(userBookmark);
    }

    @Override
    public void delete(Integer id) {
        userBookmarkMapper.delete(id);
    }
}
