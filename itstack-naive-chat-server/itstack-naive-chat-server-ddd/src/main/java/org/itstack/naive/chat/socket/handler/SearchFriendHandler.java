package org.itstack.naive.chat.socket.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import org.itstack.naive.chat.application.UserService;
import org.itstack.naive.chat.domain.user.model.LuckUserInfo;
import org.itstack.naive.chat.protocol.friend.SearchFriendRequest;
import org.itstack.naive.chat.protocol.friend.SearchFriendResponse;
import org.itstack.naive.chat.protocol.friend.dto.UserDto;
import org.itstack.naive.chat.socket.MyBizHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
@ChannelHandler.Sharable
public class SearchFriendHandler extends MyBizHandler<SearchFriendRequest> {

    private static final SearchFriendHandler searchFriendHandler = new SearchFriendHandler();

    private SearchFriendHandler() {
    }

    public static SearchFriendHandler getInstance(UserService userService){
        searchFriendHandler.userService = userService;
        return searchFriendHandler;
    }
    @Override
    public void channelRead0(ChannelHandlerContext ctx, SearchFriendRequest msg) {
        logger.info("搜索好友请求处理：{}", JSON.toJSONString(msg));
        List<UserDto> userDtoList = new ArrayList<>();
        List<LuckUserInfo> userInfoList = userService.queryFuzzyUserInfoList(msg.getUserId(), msg.getSearchKey());
        for (LuckUserInfo userInfo : userInfoList) {
            UserDto userDto = new UserDto();
            userDto.setUserId(userInfo.getUserId());
            userDto.setUserNickName(userInfo.getUserNickName());
            userDto.setUserHead(userInfo.getUserHead());
            userDto.setStatus(userInfo.getStatus());
            userDtoList.add(userDto);
        }
        SearchFriendResponse response = new SearchFriendResponse();
        response.setList(userDtoList);
        ctx.channel().writeAndFlush(response);
    }

}
