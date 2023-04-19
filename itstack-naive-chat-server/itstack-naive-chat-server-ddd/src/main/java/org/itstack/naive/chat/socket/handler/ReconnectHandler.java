package org.itstack.naive.chat.socket.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import org.itstack.naive.chat.application.UserService;
import org.itstack.naive.chat.infrastructure.common.SocketChannelUtil;
import org.itstack.naive.chat.protocol.login.ReconnectRequest;
import org.itstack.naive.chat.protocol.msg.MsgRequest;
import org.itstack.naive.chat.socket.MyBizHandler;

import java.util.List;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 * <p>
 * 重连处理
 */
@ChannelHandler.Sharable
public class ReconnectHandler extends MyBizHandler<ReconnectRequest> {

    private static final ReconnectHandler reconnectHandler = new ReconnectHandler();

    private ReconnectHandler() {
    }

    public static ReconnectHandler getInstance(UserService userService){
        reconnectHandler.userService = userService;
        return reconnectHandler;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ReconnectRequest msg) {
        logger.info("客户端断线重连处理。userId：{}", msg.getUserId());
        // 添加用户Channel
        SocketChannelUtil.removeUserChannelByUserId(msg.getUserId());
        SocketChannelUtil.addChannel(msg.getUserId(), ctx.channel());
        // 添加群组Channel
        List<String> groupsIdList = userService.queryTalkBoxGroupsIdList(msg.getUserId());
        for (String groupsId : groupsIdList) {
            SocketChannelUtil.addChannelGroup(groupsId, ctx.channel());
        }
    }

}
