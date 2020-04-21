package org.itstack.naive.chat.ui.view.chat;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.itstack.naive.chat.ui.view.chat.data.RemindCount;
import org.itstack.naive.chat.ui.view.chat.data.TalkBoxData;
import org.itstack.naive.chat.ui.view.chat.element.group_bar_friend.*;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 * <p>
 * 聊天窗口展示与事件
 */
public class ChatView {

    private ChatInit chatInit;
    private IChatEvent chatEvent;

    public ChatView(ChatInit chatInit, IChatEvent chatEvent) {
        this.chatInit = chatInit;
        this.chatEvent = chatEvent;

        //1. 好友列表添加工具方法‘新的朋友’
        initAddFriendLuck();
        //2. 好友列表添加‘公众号’
        addFriendSubscription();
        //3. 好友群组框体
        addFriendGroupList();
        //4. 好友框体
        addFriendUserList();
    }

    /**
     * 好友列表添加工具方法‘新的朋友’
     */
    private void initAddFriendLuck() {
        ListView<Pane> friendList = chatInit.$("friendList", ListView.class);
        ObservableList<Pane> items = friendList.getItems();

        ElementFriendTag elementFriendTag = new ElementFriendTag("新的朋友");
        items.add(elementFriendTag.pane());

        ElementFriendLuck element = new ElementFriendLuck();
        Pane pane = element.pane();
        items.add(pane);

        // 面板填充和事件
        pane.setOnMousePressed(event -> {
            Pane friendLuckPane = element.friendLuckPane();
            setContentPaneBox("itstack-naive-chat-ui-chat-friend-luck", "新的朋友", friendLuckPane);
            chatInit.clearViewListSelectedAll(chatInit.$("userListView", ListView.class), chatInit.$("groupListView", ListView.class));
            ListView<Pane> listView = element.friendLuckListView();
            listView.getItems().clear();
            chatEvent.addFriendLuck(chatInit.userId, listView);
        });

        // 搜索框事件
        TextField friendLuckSearch = element.friendLuckSearch();

        // 键盘事件；搜索好友
        friendLuckSearch.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                String text = friendLuckSearch.getText();
                if (null == text) text = "";
                if (text.length() > 30) text = text.substring(0, 30);
                text = text.trim();
                chatEvent.doFriendLuckSearch(chatInit.userId, text);
                // 搜索清空元素
                element.friendLuckListView().getItems().clear();
            }
        });

    }

    /**
     * 好友列表添加‘公众号’
     */
    private void addFriendSubscription() {
        ListView<Pane> friendList = chatInit.$("friendList", ListView.class);
        ObservableList<Pane> items = friendList.getItems();

        ElementFriendTag elementFriendTag = new ElementFriendTag("公众号");
        items.add(elementFriendTag.pane());

        ElementFriendSubscription element = new ElementFriendSubscription();
        Pane pane = element.pane();
        items.add(pane);

        pane.setOnMousePressed(event -> {
            chatInit.clearViewListSelectedAll(chatInit.$("userListView", ListView.class), chatInit.$("groupListView", ListView.class));
            Pane subPane = element.subPane();
            setContentPaneBox("itstack-naive-chat-ui-chat-friend-subscription", "公众号", subPane);
        });
    }

    /**
     * 好友群组框体
     */
    private void addFriendGroupList() {
        ListView<Pane> friendList = chatInit.$("friendList", ListView.class);
        ObservableList<Pane> items = friendList.getItems();

        ElementFriendTag elementFriendTag = new ElementFriendTag("群聊");
        items.add(elementFriendTag.pane());

        ElementFriendGroupList element = new ElementFriendGroupList();
        Pane pane = element.pane();
        items.add(pane);
    }

    /**
     * 好友框体
     */
    private void addFriendUserList() {
        ListView<Pane> friendList = chatInit.$("friendList", ListView.class);
        ObservableList<Pane> items = friendList.getItems();

        ElementFriendTag elementFriendTag = new ElementFriendTag("好友");
        items.add(elementFriendTag.pane());

        ElementFriendUserList element = new ElementFriendUserList();
        Pane pane = element.pane();
        items.add(pane);
    }

    /**
     * group_bar_chat：填充对话列表 & 对话框名称
     *
     * @param id   用户、群组等ID
     * @param name 用户、群组等名称
     * @param node 展现面板
     */
    void setContentPaneBox(String id, String name, Node node) {
        // 填充对话列表
        Pane content_pane_box = chatInit.$("content_pane_box", Pane.class);
        content_pane_box.setUserData(id);
        content_pane_box.getChildren().clear();
        content_pane_box.getChildren().add(node);
        // 对话框名称
        Label info_name = chatInit.$("content_name", Label.class);
        info_name.setText(name);
    }

    /**
     * 更新对话框列表元素位置指定并选中[在聊天消息发送时触达]
     */
    /**
     * @param talkType        对话框类型[0好友、1群组]
     * @param talkElementPane 对话框元素面板
     * @param msgRemindLabel  消息提醒标签
     * @param idxFirst        是否设置首位
     * @param selected        是否选中
     * @param isRemind        是否提醒
     */
    void updateTalkListIdxAndSelected(int talkType, Pane talkElementPane, Label msgRemindLabel, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        // 对话框ID、好友ID
        TalkBoxData talkBoxData = (TalkBoxData) talkElementPane.getUserData();
        // 填充到对话框
        ListView<Pane> talkList = chatInit.$("talkList", ListView.class);
        // 对话空为空，初始化[置顶、选中、提醒]
        if (talkList.getItems().isEmpty()) {
            if (idxFirst) {
                talkList.getItems().add(0, talkElementPane);
            }
            if (selected) {
                // 设置对话框[√选中]
                talkList.getSelectionModel().select(talkElementPane);
            }
            isRemind(msgRemindLabel, talkType, isRemind);
            return;
        }
        // 对话空不为空，判断第一个元素是否当前聊天Pane
        Pane firstPane = talkList.getItems().get(0);
        // 判断元素是否在首位，如果是首位可返回不需要重新设置首位
        if (talkBoxData.getTalkId().equals(((TalkBoxData) firstPane.getUserData()).getTalkId())) {
            Pane selectedItem = talkList.getSelectionModel().getSelectedItem();
            // 选中判断；如果第一个元素已经选中[说明正在会话]，那么清空消息提醒
            if (null == selectedItem){
                isRemind(msgRemindLabel, talkType, isRemind);
                return;
            }
            TalkBoxData selectedItemUserData = (TalkBoxData) selectedItem.getUserData();
            if (null != selectedItemUserData && talkBoxData.getTalkId().equals(selectedItemUserData.getTalkId())) {
                clearRemind(msgRemindLabel);
            } else {
                isRemind(msgRemindLabel, talkType, isRemind);
            }
            return;
        }
        if (idxFirst) {
            talkList.getItems().remove(talkElementPane);
            talkList.getItems().add(0, talkElementPane);
        }
        if (selected) {
            // 设置对话框[√选中]
            talkList.getSelectionModel().select(talkElementPane);
        }
        isRemind(msgRemindLabel, talkType, isRemind);
    }

    /**
     * 消息提醒
     *
     * @param msgRemindLabel 消息面板
     */
    private void isRemind(Label msgRemindLabel, int talkType, Boolean isRemind) {
        if (!isRemind) return;
        msgRemindLabel.setVisible(true);
        // 群组直接展示小红点
        if (1 == talkType) {
            return;
        }
        RemindCount remindCount = (RemindCount) msgRemindLabel.getUserData();
        // 超过10个展示省略号
        if (remindCount.getCount() > 99) {
            msgRemindLabel.setText("···");
            return;
        }
        int count = remindCount.getCount() + 1;
        msgRemindLabel.setUserData(new RemindCount(count));
        msgRemindLabel.setText(String.valueOf(count));
    }

    private void clearRemind(Label msgRemindLabel) {
        msgRemindLabel.setVisible(false);
        msgRemindLabel.setUserData(new RemindCount(0));
    }
}
