package org.itstack.naive.chat.ui;

import javafx.stage.Stage;
import org.itstack.naive.chat.ui.view.chat.ChatController;
import org.itstack.naive.chat.ui.view.chat.IChatMethod;
import org.itstack.naive.chat.ui.view.login.ILoginMethod;
import org.itstack.naive.chat.ui.view.login.LoginController;

/**
 * 窗口          Stage
 *      -场景    Scene
 *      -布局    stackPane
 *      -控件    Button
 */
public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        IChatMethod chat = new ChatController();
        chat.doShow();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
