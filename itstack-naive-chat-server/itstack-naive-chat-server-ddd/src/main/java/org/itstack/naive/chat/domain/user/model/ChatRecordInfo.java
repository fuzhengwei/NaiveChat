package org.itstack.naive.chat.domain.user.model;

import org.itstack.naive.chat.infrastructure.common.Constants;

import java.util.Date;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public class ChatRecordInfo {

    private String userId;     // 用户ID
    private String friendId;   // 好友ID
    private String msgContent; // 消息内容
    private Integer msgType;   // 消息类型；0文字消息、1固定表情
    private Date msgDate;      // 消息时间
    private Integer talkType;  // 对话框类型；0好友、1群组

    public ChatRecordInfo() {
    }

    public ChatRecordInfo(String userId, String friendId, String msgContent, Integer msgType, Date msgDate) {
        this.friendId = friendId;
        this.userId = userId;
        this.msgContent = msgContent;
        this.msgType = msgType;
        this.msgDate = msgDate;
        this.talkType = Constants.TalkType.Friend.getCode();
    }

    public ChatRecordInfo(String userId, String friendId, String msgContent, Integer msgType, Date msgDate, Integer talkType) {
        this.friendId = friendId;
        this.userId = userId;
        this.msgContent = msgContent;
        this.msgType = msgType;
        this.msgDate = msgDate;
        this.talkType = talkType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }

    public Integer getTalkType() {
        return talkType;
    }

    public void setTalkType(Integer talkType) {
        this.talkType = talkType;
    }
}
