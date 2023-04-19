package org.itstack.naive.chat.socket.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import org.itstack.naive.chat.application.UserService;
import org.itstack.naive.chat.domain.user.model.ChatRecordInfo;
import org.itstack.naive.chat.domain.user.model.UserInfo;
import org.itstack.naive.chat.infrastructure.common.Constants;
import org.itstack.naive.chat.infrastructure.common.SocketChannelUtil;
import org.itstack.naive.chat.protocol.msg.MsgGroupRequest;
import org.itstack.naive.chat.protocol.msg.MsgGroupResponse;
import org.itstack.naive.chat.socket.MyBizHandler;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 * <p>
 * 群组消息发送
 */
@ChannelHandler.Sharable
public class MsgGroupHandler extends MyBizHandler<MsgGroupRequest> {

    private static final MsgGroupHandler msgGroupHandler = new MsgGroupHandler();

    private MsgGroupHandler() {
    }

    public static MsgGroupHandler getInstance(UserService userService){
        msgGroupHandler.userService = userService;
        return msgGroupHandler;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, MsgGroupRequest msg) {
        // 获取群组通信管道
        ChannelGroup channelGroup = SocketChannelUtil.getChannelGroup(msg.getTalkId());
        if (null == channelGroup) {
            SocketChannelUtil.addChannelGroup(msg.getTalkId(), ctx.channel());
            channelGroup = SocketChannelUtil.getChannelGroup(msg.getTalkId());
        }
        // 异步写库
        userService.asyncAppendChatRecord(new ChatRecordInfo(msg.getUserId(), msg.getTalkId(), msg.getMsgText(), msg.getMsgType(), msg.getMsgDate(), Constants.TalkType.Group.getCode()));
        // 群发消息
        UserInfo userInfo = userService.queryUserInfo(msg.getUserId());
        MsgGroupResponse msgGroupResponse = new MsgGroupResponse();
        msgGroupResponse.setTalkId(msg.getTalkId());
        msgGroupResponse.setUserId(msg.getUserId());
        msgGroupResponse.setUserNickName(userInfo.getUserNickName());
        msgGroupResponse.setUserHead(userInfo.getUserHead());
        msgGroupResponse.setMsg(msg.getMsgText());
        msgGroupResponse.setMsgType(msg.getMsgType());
        msgGroupResponse.setMsgDate(msg.getMsgDate());
        channelGroup.writeAndFlush(msgGroupResponse);
    }

}
