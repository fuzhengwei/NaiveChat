package org.itstack.naive.chat.domain.user.service;

import org.itstack.naive.chat.application.UserService;
import org.itstack.naive.chat.domain.user.model.*;
import org.itstack.naive.chat.domain.user.repository.IUserRepository;
import org.itstack.naive.chat.infrastructure.po.UserFriend;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private IUserRepository userRepository;
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    //默认线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);


    @Override
    public boolean checkAuth(String userId, String userPassword) {
        // 简单比对验证
        String authCode = userRepository.queryUserPassword(userId);
        return userPassword.equals(authCode);
    }

    @Override
    public UserInfo queryUserInfo(String userId) {
        return userRepository.queryUserInfo(userId);
    }

    @Override
    public List<TalkBoxInfo> queryTalkBoxInfoList(String userId) {
        return userRepository.queryTalkBoxInfoList(userId);
    }

    @Override
    public void addTalkBoxInfo(String userId, String talkId, Integer talkType) {
        userRepository.addTalkBoxInfo(userId, talkId, talkType);
    }

    @Override
    public List<UserFriendInfo> queryUserFriendInfoList(String userId) {
        return userRepository.queryUserFriendInfoList(userId);
    }

    @Override
    public List<GroupsInfo> queryUserGroupInfoList(String userId) {
        return userRepository.queryUserGroupInfoList(userId);
    }

    @Override
    public List<LuckUserInfo> queryFuzzyUserInfoList(String userId, String searchKey) {
        return userRepository.queryFuzzyUserInfoList(userId, searchKey);
    }

    @Override
    public void addUserFriend(List<UserFriend> userFriendList) {
        userRepository.addUserFriend(userFriendList);
    }

    @Override
    public void asyncAppendChatRecord(final ChatRecordInfo chatRecordInfo) {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userRepository.appendChatRecord(chatRecordInfo);
            }
        });
    }

    @Override
    public List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer talkType) {
        return userRepository.queryChatRecordInfoList(talkId, userId, talkType);
    }

    @Override
    public void deleteUserTalk(String userId, String talkId) {
        userRepository.deleteUserTalk(userId, talkId);
    }

    @Override
    public List<String> queryUserGroupsIdList(String userId) {
        return userRepository.queryUserGroupsIdList(userId);
    }

    @Override
    public List<String> queryTalkBoxGroupsIdList(String userId) {
        return userRepository.queryTalkBoxGroupsIdList(userId);
    }

}
