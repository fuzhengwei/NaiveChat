package org.itstack.naive.chat.domain.user.model;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public class LuckUserInfo {

    private String userId;       //用户ID
    private String userNickName; //用户昵称
    private String userHead;     //用户头像
    private Integer status;      // 状态；0添加、1[保留]、2已添加

    public LuckUserInfo() {
    }

    public LuckUserInfo(String userId, String userNickName, String userHead) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.userHead = userHead;
    }

    public LuckUserInfo(String userId, String userNickName, String userHead, Integer status) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.userHead = userHead;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
