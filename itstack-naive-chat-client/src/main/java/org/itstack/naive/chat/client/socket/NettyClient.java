package org.itstack.naive.chat.client.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.itstack.naive.chat.client.application.UIService;
import org.itstack.naive.chat.client.infrastructure.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public class NettyClient implements Callable<Channel> {

    private Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private String inetHost = "127.0.0.1";
    private int inetPort = 7397;

    private EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;

    private UIService uiService;

    public NettyClient(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    public Channel call() throws Exception {
        ChannelFuture channelFuture = null;
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.AUTO_READ, true);
            b.handler(new MyChannelInitializer(uiService));
            channelFuture = b.connect(inetHost, inetPort).syncUninterruptibly();
            this.channel = channelFuture.channel();
            BeanUtil.addBean("channel", channel);
        } catch (Exception e) {
            logger.error("socket client start error", e.getMessage());
        } finally {
            if (null != channelFuture && channelFuture.isSuccess()) {
                logger.info("socket client start done. ");
            } else {
                logger.error("socket client start error. ");
            }
        }
        return channel;
    }

    public void destroy() {
        if (null == channel) return;
        channel.close();
        workerGroup.shutdownGracefully();
    }

    public boolean isActive(){
        return channel.isActive();
    }

    public Channel channel() {
        return channel;
    }

}
