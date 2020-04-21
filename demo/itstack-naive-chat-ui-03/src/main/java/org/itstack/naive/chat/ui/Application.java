package org.itstack.naive.chat.ui;

import javafx.stage.Stage;
import org.itstack.naive.chat.ui.view.Login;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Login login = new Login();
        login.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
