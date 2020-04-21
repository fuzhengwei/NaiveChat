package org.itstack.naive.chat.client;

import io.netty.channel.Channel;
import javafx.stage.Stage;
import org.itstack.naive.chat.client.event.ChatEvent;
import org.itstack.naive.chat.client.event.LoginEvent;
import org.itstack.naive.chat.client.socket.NettyClient;
import org.itstack.naive.chat.ui.view.chat.ChatController;
import org.itstack.naive.chat.ui.view.chat.IChatMethod;
import org.itstack.naive.chat.ui.view.login.ILoginMethod;
import org.itstack.naive.chat.ui.view.login.LoginController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 微信公众号：bugstack虫洞栈 | 欢迎关注学习专题案例
 * 论坛：http://bugstack.cn
 * Create by 小傅哥 on @2020
 */
public class Application extends javafx.application.Application {

    //默认线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. 启动窗口
        IChatMethod chat = new ChatController(new ChatEvent());
        ILoginMethod login = new LoginController(new LoginEvent(), chat);
        login.doShow();

        // 2. 启动socket连接
        NettyClient nettyClient = new NettyClient();
        Future<Channel> future = executorService.submit(nettyClient);
        Channel channel = future.get();
        if (null == channel) throw new RuntimeException("netty client start error channel is null");

        while (!nettyClient.isActive()) {
            Thread.sleep(500);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
