package org.itstack.naive.chat.socket.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.itstack.naive.chat.application.UserService;
import org.itstack.naive.chat.protocol.Command;
import org.itstack.naive.chat.protocol.Packet;
import org.itstack.naive.chat.socket.MyBizHandler;

import java.util.HashMap;
import java.util.Map;

@ChannelHandler.Sharable
public class IMHandler extends MyBizHandler<Packet> {

    private static volatile IMHandler INSTANCE = null;

    private final Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap = new HashMap<>();

    private IMHandler(UserService userService){
        handlerMap.put(Command.AddFriendRequest, AddFriendHandler.getInstance(userService));
        handlerMap.put(Command.DelTalkRequest, DelTalkHandler.getInstance(userService));
        handlerMap.put(Command.LoginRequest, LoginHandler.getInstance(userService));
        handlerMap.put(Command.MsgGroupRequest, MsgGroupHandler.getInstance(userService));
        handlerMap.put(Command.MsgRequest, MsgHandler.getInstance(userService));
        handlerMap.put(Command.ReconnectRequest, ReconnectHandler.getInstance(userService));
        handlerMap.put(Command.SearchFriendRequest, SearchFriendHandler.getInstance(userService));
        handlerMap.put(Command.TalkNoticeRequest, TalkNoticeHandler.getInstance(userService));
    }

    public static IMHandler getINSTANCE(UserService userService) {
        if (INSTANCE == null){
            synchronized (IMHandler.class){
                if (INSTANCE == null){
                    INSTANCE = new IMHandler(userService);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        handlerMap.get(msg.getCommand()).channelRead(ctx,msg);
    }
}
