package org.itstack.naive.chat.client.event;

import io.netty.channel.Channel;
import org.itstack.naive.chat.client.infrastructure.util.BeanUtil;
import org.itstack.naive.chat.client.infrastructure.util.CacheUtil;
import org.itstack.naive.chat.protocol.login.LoginRequest;
import org.itstack.naive.chat.ui.view.login.ILoginEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 * <p>
 * 事件实现；登陆窗口
 */
public class LoginEvent implements ILoginEvent {

    private Logger logger = LoggerFactory.getLogger(LoginEvent.class);

    @Override
    public void doLoginCheck(String userId, String userPassword) {
        Channel channel = BeanUtil.getBean("channel", Channel.class);
        channel.writeAndFlush(new LoginRequest(userId, userPassword));
        CacheUtil.userId = userId;
    }

}
