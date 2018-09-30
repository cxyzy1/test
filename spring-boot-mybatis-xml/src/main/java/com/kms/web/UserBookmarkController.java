package com.kms.web;

import com.kms.entity.UserBookmark;
import com.kms.service.UserBookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ubk")
public class UserBookmarkController extends BaseController {

    @Autowired
    private UserBookmarkService userBookmarkService;

    @RequestMapping("/list")
    public List<UserBookmark> list() {
        List<UserBookmark> userBookmarks = userBookmarkService.list();
        return userBookmarks;
    }

    @RequestMapping("/get")
    public UserBookmark get(Integer id) {
        UserBookmark userBookmark = userBookmarkService.get(id);
        return userBookmark;
    }

    @RequestMapping("/add")
    public void add(UserBookmark userBookmark) {
        userBookmark.setBookmarkUrl("http://www.baidu.com");
        userBookmarkService.add(userBookmark);
    }

    @RequestMapping(value = "update")
    public void update(UserBookmark userBookmark) {
        userBookmarkService.update(userBookmark);
    }

    @RequestMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userBookmarkService.delete(id);
    }


}