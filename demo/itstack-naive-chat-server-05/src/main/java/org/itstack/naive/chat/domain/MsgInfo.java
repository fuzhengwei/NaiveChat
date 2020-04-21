package org.itstack.naive.chat.domain;

/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：bugstack虫洞栈  ｛关注获取学习源码｝
 * Create by 小傅哥 on 2020
 */
public class MsgInfo {

    private String channelId;
    private String msgContent;

    public MsgInfo() {
    }

    public MsgInfo(String channelId, String msgContent) {
        this.channelId = channelId;
        this.msgContent = msgContent;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}
