package org.itstack.naive.chat.application;

import org.itstack.naive.chat.domain.inet.model.ChannelUserInfo;
import org.itstack.naive.chat.domain.inet.model.ChannelUserReq;
import org.itstack.naive.chat.domain.inet.model.InetServerInfo;

import java.util.List;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 *
 * 网络信息查询
 */
public interface InetService {

    InetServerInfo queryNettyServerInfo();

    Long queryChannelUserCount(ChannelUserReq req);

    List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req);

}
