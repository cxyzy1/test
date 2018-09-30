package com.kms.service;

import com.kms.entity.User;
import com.kms.entity.UserBookmark;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBookmarkServiceTest {

    @Autowired
    private UserBookmarkService userBookmarkService;

    @Autowired
    private UserService userService;

    private Integer userId;

    @Before
    public void init(){
        userService.add(new User("testUser","123456"));
        User user = userService.list().get(0);
        userId = user.getId();
    }

    @Test
    public void testAdd() {
        int originalCount = userBookmarkService.list().size();
        add(userId,"http://www.baidu.com","百度一下","");
        Assert.assertEquals(originalCount + 1, userBookmarkService.list().size());
    }

    @Test
    public void testList() {
        add(userId,"http://www.baidu.com","百度一下","");
        List<UserBookmark> userBookmarks = userBookmarkService.list();
        Assert.assertFalse(userBookmarks.isEmpty());
    }

    @Test
    public void testUpdate() {
//        add(userId,"http://www.baidu.com","百度一下","");
//        final String name = "kms123";
//        UserBookmark userBookmark = userBookmarkService.list().get(0);
//        System.out.println(userBookmark.toString());
//        userBookmarkService.update(userBookmark);
//        Assert.assertTrue((name.equals(userBookmarkService.get(userBookmark.getId()).getName())));
    }

    @Test
    public void testDelete() {
        add(userId,"http://www.baidu.com","百度一下","");
        UserBookmark userBookmark = userBookmarkService.list().get(0);
        userBookmarkService.delete(userBookmark.getId());
        Assert.assertNull(userBookmarkService.get(userBookmark.getId()));
    }

    private void add(Integer userId, String bookmarkUrl, String bookmarkTitle, String tagNames) {
        userBookmarkService.add(new UserBookmark(userId, bookmarkUrl, bookmarkTitle, tagNames));
    }

}