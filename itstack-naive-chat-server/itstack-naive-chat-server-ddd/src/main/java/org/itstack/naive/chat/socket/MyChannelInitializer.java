package org.itstack.naive.chat.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.itstack.naive.chat.application.UserService;
import org.itstack.naive.chat.codec.ObjDecoder;
import org.itstack.naive.chat.codec.ObjEncoder;
import org.itstack.naive.chat.socket.handler.*;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    private UserService userService;

    public MyChannelInitializer(UserService userService){
        this.userService = userService;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //对象传输处理[解码]
        channel.pipeline().addLast(new ObjDecoder());
        // 在管道中添加我们自己的接收数据实现方法
//        channel.pipeline().addLast(AddFriendHandler.getInstance(userService));
//        channel.pipeline().addLast(DelTalkHandler.getInstance(userService));
//        channel.pipeline().addLast(LoginHandler.getInstance(userService));
//        channel.pipeline().addLast(MsgGroupHandler.getInstance(userService));
//        channel.pipeline().addLast(MsgHandler.getInstance(userService));
//        channel.pipeline().addLast(ReconnectHandler.getInstance(userService));
//        channel.pipeline().addLast(SearchFriendHandler.getInstance(userService));
//        channel.pipeline().addLast(TalkNoticeHandler.getInstance(userService));
        channel.pipeline().addLast(IMHandler.getINSTANCE(userService));
        //对象传输处理[编码]
        channel.pipeline().addLast(new ObjEncoder());
    }

}
