package org.itstack.naive.chat.test;

import com.alibaba.fastjson.JSON;
import org.itstack.naive.chat.Application;
import org.itstack.naive.chat.application.UserService;
import org.itstack.naive.chat.domain.user.model.ChatRecordInfo;
import org.itstack.naive.chat.infrastructure.common.Constants;
import org.itstack.naive.chat.infrastructure.dao.IUserDao;
import org.itstack.naive.chat.infrastructure.dao.IUserFriendDao;
import org.itstack.naive.chat.infrastructure.po.User;
import org.itstack.naive.chat.infrastructure.po.UserFriend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApiTest {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IUserFriendDao userFriendDao;
    @Autowired
    private UserService userService;

    @Test
    public void test_queryFuzzyUserList() {
        List<User> userList = userDao.queryFuzzyUserList("184172133", "");
        System.out.println(JSON.toJSONString(userList));
    }

    @Test
    public void test_addUserFriendList() {
        List<UserFriend> userFriendList = new ArrayList<>();
        userFriendList.add(new UserFriend("11", "22"));
        userFriendList.add(new UserFriend("22", "11"));
        userFriendDao.addUserFriendList(userFriendList);
    }

    @Test
    public void test_asyncAppendChatRecord() throws InterruptedException {

        ChatRecordInfo chatRecordInfo = new ChatRecordInfo();
        chatRecordInfo.setUserId("184172133");
        chatRecordInfo.setFriendId("523088136");
        chatRecordInfo.setMsgContent("我是哈尼克兔你在吗");
        chatRecordInfo.setMsgDate(new Date());
        userService.asyncAppendChatRecord(chatRecordInfo);
        Thread.sleep(10000);
    }


}


