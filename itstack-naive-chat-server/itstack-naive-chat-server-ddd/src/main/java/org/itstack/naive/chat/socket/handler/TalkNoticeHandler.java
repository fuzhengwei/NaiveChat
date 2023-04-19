package org.itstack.naive.chat.socket.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.itstack.naive.chat.application.UserService;
import org.itstack.naive.chat.domain.user.model.UserInfo;
import org.itstack.naive.chat.infrastructure.common.Constants;
import org.itstack.naive.chat.infrastructure.common.SocketChannelUtil;
import org.itstack.naive.chat.protocol.talk.TalkNoticeRequest;
import org.itstack.naive.chat.protocol.talk.TalkNoticeResponse;
import org.itstack.naive.chat.socket.MyBizHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 * <p>
 * 对话通知应答处理
 */
@ChannelHandler.Sharable
public class TalkNoticeHandler extends MyBizHandler<TalkNoticeRequest> {

    private static final TalkNoticeHandler talkNoticeHandler = new TalkNoticeHandler();

    private TalkNoticeHandler() {
    }

    public static TalkNoticeHandler getInstance(UserService userService){
        talkNoticeHandler.userService = userService;
        return talkNoticeHandler;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, TalkNoticeRequest msg) {
        logger.info("对话通知应答处理：{}", JSON.toJSONString(msg));

        switch (msg.getTalkType()) {
            case 0:
                // 1. 对话框数据落库
                userService.addTalkBoxInfo(msg.getUserId(), msg.getFriendUserId(), 0);
                userService.addTalkBoxInfo(msg.getFriendUserId(), msg.getUserId(), 0);
                // 2. 查询对话框信息[将自己发给好友的对话框中]
                UserInfo userInfo = userService.queryUserInfo(msg.getUserId());
                // 3. 发送对话框消息给好友
                TalkNoticeResponse response = new TalkNoticeResponse();
                response.setTalkId(userInfo.getUserId());
                response.setTalkName(userInfo.getUserNickName());
                response.setTalkHead(userInfo.getUserHead());
                response.setTalkSketch(null);
                response.setTalkDate(new Date());
                // 获取好友通信管道
                Channel friendChannel = SocketChannelUtil.getChannel(msg.getFriendUserId());
                if (null == friendChannel) {
                    logger.info("用户id：{}未登录！", msg.getFriendUserId());
                    return;
                }
                friendChannel.writeAndFlush(response);
                break;
            case 1:
                userService.addTalkBoxInfo(msg.getUserId(), msg.getFriendUserId(), 1);
                break;
            default:
                break;
        }

    }

}
