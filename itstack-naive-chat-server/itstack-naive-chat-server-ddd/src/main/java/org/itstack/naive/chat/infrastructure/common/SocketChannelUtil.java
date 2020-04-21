package org.itstack.naive.chat.infrastructure.common;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public class SocketChannelUtil {

    // 用户
    private static Map<String, Channel> userChannel = new ConcurrentHashMap<>();
    private static Map<String, String> userChannelId = new ConcurrentHashMap<>();
    // 群组
    private static Map<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();

    public static void addChannel(String userId, Channel channel) {
        userChannel.put(userId, channel);
        userChannelId.put(channel.id().toString(), userId);
    }

    public static void removeChannel(String channelId){
        String userId = userChannelId.get(channelId);
        if (null == userId) return;
        userChannel.remove(userId);
    }

    public static void removeUserChannelByUserId(String userId){
        userChannel.remove(userId);
    }

    public static Channel getChannel(String userId) {
        return userChannel.get(userId);
    }

    /**
     * 添加群组成员通信管道
     *
     * @param talkId      对话框ID[群号]
     * @param userChannel 群员通信管道
     */
    public synchronized static void addChannelGroup(String talkId, Channel userChannel) {
        ChannelGroup channelGroup = channelGroupMap.get(talkId);
        if (null == channelGroup) {
            channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            channelGroupMap.put(talkId, channelGroup);
        }
        channelGroup.add(userChannel);
    }

    public synchronized static void removeChannelGroup(String talkId, Channel userChannel){
        ChannelGroup channelGroup = channelGroupMap.get(talkId);
        if (null == channelGroup) return;
        channelGroup.remove(userChannel);
    }

    public static void removeChannelGroupByChannel(Channel channel){
        for (ChannelGroup next : channelGroupMap.values()) {
            next.remove(channel);
        }
    }

    /**
     * 获取群组通信管道
     *
     * @param talkId 对话框ID[群号]
     * @return 群组
     */
    public static ChannelGroup getChannelGroup(String talkId) {
        return channelGroupMap.get(talkId);
    }

}
