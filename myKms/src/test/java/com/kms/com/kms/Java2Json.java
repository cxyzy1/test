package com.kms.com.kms;

import com.alibaba.fastjson.JSON;
import com.kms.com.kms.entity.User;
import com.kms.com.kms.entity.UserBookmark;

public class Java2Json {

    public static void main(String[] args)
    {
//        User user = new User();
//        user.setName("testUser");
//        System.out.println(JSON.toJSONString(user));
        UserBookmark userBookmark = new UserBookmark();
        userBookmark.setId("111");
        userBookmark.setProgress((short)80);
        System.out.println(JSON.toJSONString(userBookmark));
    }

}
