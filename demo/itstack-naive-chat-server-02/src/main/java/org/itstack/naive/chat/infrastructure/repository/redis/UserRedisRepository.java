package org.itstack.naive.chat.infrastructure.repository.redis;

import com.alibaba.fastjson.JSON;
import org.itstack.naive.chat.domain.repository.IUserRepository;
import org.itstack.naive.chat.infrastructure.po.UserEntity;
import org.itstack.naive.chat.infrastructure.util.RedisUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：bugstack虫洞栈 | 欢迎关注并获取更多专题案例源码
 * Create by 小傅哥 on @2020
 */
@Repository("userRedisRepository")
public class UserRedisRepository implements IUserRepository {

    @Resource(name = "redisUtil")
    private RedisUtil redisUtil;

    @Override
    public void save(UserEntity userEntity) {
        redisUtil.set("itstack-demo-ddd-userinfo", JSON.toJSONString(userEntity));
    }

    @Override
    public UserEntity query(Long id) {
        String objJson = redisUtil.get("itstack-demo-ddd-userinfo");
        return JSON.parseObject(objJson, UserEntity.class);
    }

}
