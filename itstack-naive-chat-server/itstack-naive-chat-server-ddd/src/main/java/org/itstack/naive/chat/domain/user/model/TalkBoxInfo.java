package org.itstack.naive.chat.domain.user.model;

import org.itstack.naive.chat.infrastructure.po.ChatRecord;
import org.itstack.naive.chat.protocol.login.dto.ChatRecordDto;

import java.util.Date;
import java.util.List;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public class TalkBoxInfo {

    private Integer talkType; // 对话框类型；0好友、1群组
    private String talkId;    // 对话框ID(好友ID、群组ID)
    private String talkName;  // 对话框名称
    private String talkHead;  // 对话框头像
    private String talkSketch;  // 消息简述
    private Date talkDate;      // 消息时间

    public Integer getTalkType() {
        return talkType;
    }

    public void setTalkType(Integer talkType) {
        this.talkType = talkType;
    }

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

}
