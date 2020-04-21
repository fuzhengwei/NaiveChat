package org.itstack.naive.chat.ui;

import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.itstack.naive.chat.ui.view.login.ILoginMethod;
import org.itstack.naive.chat.ui.view.login.LoginController;

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

        ILoginMethod login = new LoginController((userId, userPassword) -> {
            System.out.println("登陆 userId：" + userId + "userPassword：" + userPassword);
        });

        login.doShow();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
