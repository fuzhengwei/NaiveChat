package org.itstack.naive.chat.ui.view.chat.element.group_bar_friend;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public class ElementFriendLuckUser {

    private Pane pane;    // 用户底板

    private Label idLabel;    // 展示用户ID
    private Label headLabel;  // 头像区域
    private Label nameLabel;  // 名称区域
    private Label statusLabel;// 状态；0添加/1接受
    private Label line;       // 底线

    /**
     * 构造函数
     *
     * @param userId       用户ID
     * @param userNickName 用户昵称
     * @param userHead     用户头像
     * @param status       状态；0/1/2
     */
    public ElementFriendLuckUser(String userId, String userNickName, String userHead, Integer status) {
        pane = new Pane();
        pane.setUserData(userId);
        pane.setPrefWidth(250);
        pane.setPrefHeight(70);
        pane.getStyleClass().add("elementFriendLuckUser");
        ObservableList<Node> children = pane.getChildren();
        // 头像区域
        headLabel = new Label();
        headLabel.setPrefSize(50, 50);
        headLabel.setLayoutX(125);
        headLabel.setLayoutY(10);
        headLabel.getStyleClass().add("elementFriendLuckUser_head");
        headLabel.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
        children.add(headLabel);

        // 名称区域
        nameLabel = new Label();
        nameLabel.setPrefSize(200, 30);
        nameLabel.setLayoutX(190);
        nameLabel.setLayoutY(10);
        nameLabel.setText(userNickName);
        nameLabel.getStyleClass().add("elementFriendLuckUser_name");
        children.add(nameLabel);

        // ID区域
        idLabel = new Label();
        idLabel.setPrefSize(200, 20);
        idLabel.setLayoutX(190);
        idLabel.setLayoutY(40);
        idLabel.setText(userId);
        idLabel.getStyleClass().add("elementFriendLuckUser_id");
        children.add(idLabel);

        // 底线
        line = new Label();
        line.setPrefSize(582,1);
        line.setLayoutX(125);
        line.setLayoutY(50);
        line.getStyleClass().add("elementFriendLuck_line");
        children.add(line);

        // 状态区域
        statusLabel = new Label();
        statusLabel.setPrefSize(56, 30);
        statusLabel.setLayoutX(650);
        statusLabel.setLayoutY(20);
        String statusText = "添加";
        if (1 == status){
            statusText = "允许";
        } else if (2 == status){
            statusText = "已添加";
        }
        statusLabel.setText(statusText);
        statusLabel.setUserData(status);
        statusLabel.getStyleClass().add("elementFriendLuckUser_statusLabel_" + status);
        children.add(statusLabel);
    }

    public Pane pane() {
        return pane;
    }

    // 添加按钮
    public Label statusLabel() {
        return statusLabel;
    }
}
