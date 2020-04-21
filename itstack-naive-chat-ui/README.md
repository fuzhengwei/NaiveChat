# :heart:《Netty+JavaFx实战：仿桌面版微信聊天》| UI工程

[![stars](https://badgen.net/github/stars/itstack-naive-chat/itstack-naive-chat-ui?icon=github&color=4ab8a1)](https://github.com/itstack-naive-chat/itstack-naive-chat-ui) [![forks](https://badgen.net/github/forks/itstack-naive-chat/itstack-naive-chat-ui?icon=github&color=4ab8a1)](https://github.com/itstack-naive-chat/itstack-naive-chat-ui) [<img src="https://itstack.org/_media/onlinebook.svg">](http://chat.itstack.org) [<img src="https://itstack.org/_media/wxbugstack.svg">](https://itstack.org/_media/qrcode.png?x-oss-process=style/may)

> 本项目是作者小傅哥使用技术栈```JavaFx```、```Netty4.x```、```SpringBoot```、```Mysql```等，搭建的仿桌面版微信聊天工程实现通信核心功能。如果本项目能为您提供帮助，请给予支持(关注、点赞、分享)！

> **作者：** 小傅哥，Java Developer，[:trophy: CSDN 博客专家](https://bugstack.blog.csdn.net)，[:bug: 虫洞 · 科技栈](https://bugstack.cn)

**学习链接：**

- [《Netty+JavaFx实战：仿桌面版微信聊天》](https://chat.itstack.org)
 
**赏个鸡腿🍗**

![](https://bugstack.cn/assets/images/tip.jpg)

----

## 一、简述

此工程是使用JavaFx开发的UI端，在我们的UI端中提供了；登录框体、聊天框体，同时在聊天框体中有大量的行为交互界面以及接口和事件。最终我的UI端使用Maven打包的方式向外提供Jar包，以此来达到UI界面与业务行为流程分离。并且用户可以很方便的在我们的框架结构下进行扩展。

## 二、Maven配置

```xml
<dependency>
  <groupId>org.itstack</groupId>
  <artifactId>itstack-naive-chat-ui</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## 三、页面展示

### 1. 登录页面

![登陆页面](http://chat.itstack.org/assets/img/2020/ui-00.png)

### 2. 聊天页面

![聊天页面](http://chat.itstack.org/assets/img/2020/ui-01.png)

### 3. 添加好友

![添加好友](http://chat.itstack.org/assets/img/2020/ui-02.png)

### 4. 消息提醒

![消息提醒](http://chat.itstack.org/assets/img/2020/ui-05.png)

## 四、功能

### 1. 登录窗体

- **接口**

	| 序号 | 方法名 |  描述 |
	| :---: | :--- | :--- | 
	| 1 | void doShow() | 打开登陆窗口 | 
	| 2 | void doLoginError() | 登录失败提醒 | 
	| 3 | void doLoginSuccess() | 登陆成功；跳转聊天窗口(关闭登陆窗口，打开新窗口) | 

- **事件**

	| 序号 | 事件名 |  描述 |
	| :---: | :--- |  :--- | 
	| 1 | void doLoginCheck(String userId, String userPassword) | 登陆验证 | 


### 2. 聊天窗体

- **接口**

	| 序号 | 接口名 |  描述 |
	| :---: | :--- |  :--- | 
	| 1 | void doShow() | 打开窗口 |
	| 2 | void setUserInfo(String userId, String userNickName, String userHead) | 设置登陆用户ID、昵称、头像 | 
	| 3 | void addTalkBox(int talkIdx, Integer talkType, String talkId, String talkName, String talkHead, String talkSketch, Date talkDate, Boolean selected) | 填充对话框列表 |
	| 4 | void addTalkMsgUserLeft(String talkId, String msg, Date msgData, Boolean idxFirst, Boolean selected, Boolean isRemind) | 填充对话框消息-好友[别人的消息] |
	| 5 | void addTalkMsgGroupLeft(String talkId, String userId, String userNickName, String userHead, String msg, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) | 填充对话框消息-群组[别人的消息] |
	| 6 | void addTalkMsgRight(String talkId, String msg, Date msgData, Boolean idxFirst, Boolean selected, Boolean isRemind) | 填充对话框消息[自己的消息] |
	| 7 | void addFriendGroup(String groupId, String groupName, String groupHead) | 好友列表添加‘群组’ |
	| 8 | void addFriendUser(boolean selected, String userId, String userNickName, String userHead) | 好友列表添加‘用户’ |
	| 9 | void addLuckFriend(String userId, String userNickName, String userHead, Integer status) |  缘分好友(默认添加10个好友) |

- **事件**

	| 序号 | 事件名 |  描述 |
	| :---: | :--- |  :--- | 
	| 1 | void doQuit() | 聊天窗口退出操作 | 
	| 2 | void doSendMsg(String userId, String talkId, Integer talkType, String msg, Date msgDate) | 发送消息按钮 | 
	| 3 | void doEventAddTalkUser(String userId, String userFriendId) | 事件处理；开启与好友发送消息 [点击发送消息时候触发 -> 添加到对话框、选中、展示对话列表] | 
	| 4 | void doEventAddTalkGroup(String userId, String groupId) | 事件处理；开启与群组发送消息 | 
	| 5 | void doEventDelTalkUser(String userId, String talkId) | 事件处理；删除指定对话框 | 
	| 6 | void addFriendLuck(String userId, ListView<Pane> listView) | 事件处理；查询有缘用户添加到列表 | 
	| 7 | void doFriendLuckSearch(String userId, String text) | 事件处理；好友搜索[搜索后结果调用添加：addLuckFriend] | 
	| 8 | void doEventAddLuckUser(String userId, String friendId) | 添加好友事件 | 

----

如果本项目能为您提供帮助，请给予支持(关注、点赞、分享)！