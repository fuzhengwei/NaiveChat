package org.itstack.naive.chat.protocol.talk;

import org.itstack.naive.chat.protocol.Command;
import org.itstack.naive.chat.protocol.Packet;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 * <p>
 * 对话通知请求
 */
public class TalkNoticeRequest extends Packet {

    private String userId;       // 用户ID
    private String friendUserId; // 好友ID
    private Integer talkType;    // 对话框类型[0好友、1群组]

    public TalkNoticeRequest(){}

    public TalkNoticeRequest(String userId, String friendUserId, Integer talkType) {
        this.userId = userId;
        this.friendUserId = friendUserId;
        this.talkType = talkType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }

    public Integer getTalkType() {
        return talkType;
    }

    public void setTalkType(Integer talkType) {
        this.talkType = talkType;
    }

    @Override
    public Byte getCommand() {
        return Command.TalkNoticeRequest;
    }
}
