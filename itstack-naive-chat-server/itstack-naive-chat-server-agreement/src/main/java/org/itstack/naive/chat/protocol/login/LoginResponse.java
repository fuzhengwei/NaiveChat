package org.itstack.naive.chat.protocol.login;

import org.itstack.naive.chat.protocol.Command;
import org.itstack.naive.chat.protocol.Packet;
import org.itstack.naive.chat.protocol.login.dto.ChatTalkDto;
import org.itstack.naive.chat.protocol.login.dto.GroupsDto;
import org.itstack.naive.chat.protocol.login.dto.UserFriendDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public class LoginResponse extends Packet {

    private boolean success;                    // 登陆反馈
    private String userId;                      // 用户ID
    private String userHead;                    // 用户头像
    private String userNickName;                // 用户昵称
    private List<ChatTalkDto> chatTalkList = new ArrayList<>();     // 聊天对话框数据[success is true]
    private List<GroupsDto> groupsList = new ArrayList<>();         // 群组列表
    private List<UserFriendDto> userFriendList = new ArrayList<>(); // 好友列表

    public LoginResponse(){}

    public LoginResponse(boolean success){
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public List<ChatTalkDto> getChatTalkList() {
        return chatTalkList;
    }

    public void setChatTalkList(List<ChatTalkDto> chatTalkList) {
        this.chatTalkList = chatTalkList;
    }

    public List<GroupsDto> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<GroupsDto> groupsList) {
        this.groupsList = groupsList;
    }

    public List<UserFriendDto> getUserFriendList() {
        return userFriendList;
    }

    public void setUserFriendList(List<UserFriendDto> userFriendList) {
        this.userFriendList = userFriendList;
    }

    @Override
    public Byte getCommand() {
        return Command.LoginResponse;
    }

}




