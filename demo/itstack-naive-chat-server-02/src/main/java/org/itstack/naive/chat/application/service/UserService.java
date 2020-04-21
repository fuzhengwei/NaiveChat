package org.itstack.naive.chat.application.service;

import org.itstack.naive.chat.domain.model.aggregates.UserRichInfo;

/**
 * 应用层用户服务
 * 虫洞栈：https://bugstack.cn
 * 公众号：bugstack虫洞栈 | 欢迎关注并获取更多专题案例源码
 * Create by 小傅哥 on @2020
 */
public interface UserService {

    UserRichInfo queryUserInfoById(Long id);

}
