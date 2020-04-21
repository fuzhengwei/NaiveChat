package org.itstack.naive.chat.ui.view.chat;

import java.util.Date;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public interface IChatMethod {

    /**
     * 打开窗口
     */
    void doShow();

    /**
     * 填充对话框列表
     *
     * @param talkIdx    对话框位置；首位0、默认-1
     * @param talkType   对话框类型；好友0、群组1
     * @param talkId     对话框ID，1v1聊天ID、1vn聊天ID
     * @param talkName   对话框名称
     * @param talkHead   对话框头像
     * @param talkSketch 对话框通信简述(聊天内容最后一组信息)
     * @param talkDate   对话框通信时间
     * @param selected   选中[true/false]
     */
    void addTalkBox(int talkIdx, Integer talkType, String talkId, String talkName, String talkHead, String talkSketch, Date talkDate, Boolean selected);


}
