package org.itstack.naive.chat.ui.view.chat.element.group_bar_friend;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;


/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 * <p>
 * 组件；群组
 */
public class ElementFriendGroupList {

    private Pane pane;
    private ListView<Pane> groupListView; // 群组列表

    public ElementFriendGroupList() {
        pane = new Pane();
        pane.setId("friendGroupList");
        pane.setPrefWidth(314);
        pane.setPrefHeight(0);// 自动计算；groupListView.setPrefHeight(70 * items.size() + 10);
        pane.setLayoutX(-10);
        pane.getStyleClass().add("elementFriendGroupList");
        ObservableList<Node> children = pane.getChildren();

        groupListView = new ListView<>();
        groupListView.setId("groupListView");
        groupListView.setPrefWidth(314);
        groupListView.setPrefHeight(0); // 自动计算；groupListView.setPrefHeight(70 * items.size() + 10);
        groupListView.setLayoutX(-10);
        groupListView.getStyleClass().add("elementFriendGroupList_listView");
        children.add(groupListView);
    }

    public Pane pane() {
        return pane;
    }

}
