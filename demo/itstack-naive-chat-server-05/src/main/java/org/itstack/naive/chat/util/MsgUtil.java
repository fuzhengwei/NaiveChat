package org.itstack.naive.chat.util;

import org.itstack.naive.chat.domain.MsgInfo;

/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：bugstack虫洞栈  ｛关注获取学习源码｝
 * Create by 小傅哥 on 2020
 */
public class MsgUtil {

    public static MsgInfo buildMsg(String channelId, String msgContent) {
        return new MsgInfo(channelId,msgContent);
    }

}
