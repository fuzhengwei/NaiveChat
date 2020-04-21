package org.itstack.naive.chat.domain.repository;

import org.itstack.naive.chat.infrastructure.po.UserEntity;

/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：bugstack虫洞栈 | 欢迎关注并获取更多专题案例源码
 * Create by 小傅哥 on @2020
 */
public interface IUserRepository {

    void save(UserEntity userEntity);

    UserEntity query(Long id);

}
