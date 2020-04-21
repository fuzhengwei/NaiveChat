package org.itstack.naive.chat.infrastructure.po;

import java.util.Date;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 * <p>
 * 用户好友
 */
public class UserFriend {

    private Long id;             // 自增ID
    private String userId;       // 用户ID
    private String userFriendId; // 好友用户ID
    private Date createTime;     // 创建时间
    private Date updateTime;     // 更新时间

    public UserFriend() {
    }

    public UserFriend(String userId, String userFriendId) {
        this.userId = userId;
        this.userFriendId = userFriendId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserFriendId() {
        return userFriendId;
    }

    public void setUserFriendId(String userFriendId) {
        this.userFriendId = userFriendId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
