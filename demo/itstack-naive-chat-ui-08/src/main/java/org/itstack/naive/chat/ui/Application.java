package org.itstack.naive.chat.ui;

import javafx.stage.Stage;
import org.itstack.naive.chat.ui.view.chat.ChatController;
import org.itstack.naive.chat.ui.view.chat.IChatEvent;
import org.itstack.naive.chat.ui.view.chat.IChatMethod;

import java.util.Date;

/**
 * 窗口          Stage
 * -场景       Scene
 * -布局     stackPane
 * -控件   Button
 */
public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        IChatMethod chat = new ChatController(new IChatEvent() {
        });

        chat.doShow();
        chat.setUserInfo("1000001", "拎包冲", "02_50");

        // 好友
        chat.addTalkBox(-1, 0, "1000004", "哈尼克兔", "04_50", null, null, false);
        chat.addTalkMsgUserLeft("1000004", "沉淀、分享、成长，让自己和他人都有所收获！", new Date(), true, false, true);
        chat.addTalkMsgRight("1000004", "今年过年是放假时间最长的了！", new Date(), true, true, false);

        chat.addTalkBox(-1, 0, "1000002", "铁锤", "03_50", "秋风扫过树叶落，哪有棋盘哪有我", new Date(), false);
        chat.addTalkMsgUserLeft("1000002", "秋风扫过树叶落，哪有棋盘哪有我", new Date(), true, false, true);
        chat.addTalkMsgRight("1000002", "我Q，传说中的老头杀？", new Date(), true, true, false);

        // 群组
        chat.addFriendGroup("5307397", "虫洞 · 技术栈(1区)", "group_1");
        chat.addFriendGroup("5307392", "CSDN 社区专家", "group_2");
        chat.addFriendGroup("5307399", "洗脚城VIP", "group_3");

        // 群组 - 对话框
        chat.addTalkBox(0, 1, "5307397", "虫洞 · 技术栈(1区)", "group_1", "", new Date(), false);
        chat.addTalkMsgRight("5307397", "你炸了我的山", new Date(), true, true, false);
        chat.addTalkMsgGroupLeft("5307397", "1000002", "拎包冲", "01_50", "推我过忘川", new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000003", "铁锤", "03_50", "奈河桥边的姑娘", new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000004", "哈尼克兔", "04_50", "等我回头看", new Date(), true, false, true);

        // 好友
        chat.addFriendUser(false, "1000004", "哈尼克兔", "04_50");
        chat.addFriendUser(false, "1000001", "拎包冲", "02_50");
        chat.addFriendUser(false, "1000002", "铁锤", "03_50");
        chat.addFriendUser(true, "1000003", "小傅哥 | bugstack.cn", "01_50");

    }

    public static void main(String[] args) {
        launch(args);
    }

}
