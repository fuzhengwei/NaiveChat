package org.itstack.naive.chat.socket.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import org.itstack.naive.chat.application.UserService;
import org.itstack.naive.chat.protocol.talk.DelTalkRequest;
import org.itstack.naive.chat.socket.MyBizHandler;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
@ChannelHandler.Sharable
public class DelTalkHandler extends MyBizHandler<DelTalkRequest> {

    private static final DelTalkHandler delTalkHandler = new DelTalkHandler();

    private DelTalkHandler() {
    }

    public static DelTalkHandler getInstance(UserService userService){
        delTalkHandler.userService = userService;
        return delTalkHandler;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DelTalkRequest msg) {
        userService.deleteUserTalk(msg.getUserId(), msg.getTalkId());
    }
}
