package org.itstack.naive.chat.protocol.talk;

import org.itstack.naive.chat.protocol.Command;
import org.itstack.naive.chat.protocol.Packet;

import java.util.Date;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 * <p>
 * 对话通知应答
 */
public class TalkNoticeResponse extends Packet {

    private String talkId;     // 对话框ID[好友ID、群ID]
    private String talkName;   // 对话框名称[好友名称、群名称]
    private String talkHead;   // 对话框头像[好友头像、群头像]
    private String talkSketch; // 消息简讯
    private Date talkDate;     // 消息时间

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }

    public String getTalkName() {
        return talkName;
    }

    public void setTalkName(String talkName) {
        this.talkName = talkName;
    }

    public String getTalkHead() {
        return talkHead;
    }

    public void setTalkHead(String talkHead) {
        this.talkHead = talkHead;
    }

    public String getTalkSketch() {
        return talkSketch;
    }

    public void setTalkSketch(String talkSketch) {
        this.talkSketch = talkSketch;
    }

    public Date getTalkDate() {
        return talkDate;
    }

    public void setTalkDate(Date talkDate) {
        this.talkDate = talkDate;
    }

    @Override
    public Byte getCommand() {
        return Command.TalkNoticeResponse;
    }

}
