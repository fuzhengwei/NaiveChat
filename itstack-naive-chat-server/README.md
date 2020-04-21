# :revolving_hearts:《Netty+JavaFx实战：仿桌面版微信聊天》| 服务端

[![stars](https://badgen.net/github/stars/itstack-naive-chat/itstack-naive-chat-server?icon=github&color=4ab8a1)](https://github.com/itstack-naive-chat/itstack-naive-chat-server) [![forks](https://badgen.net/github/forks/itstack-naive-chat/itstack-naive-chat-server?icon=github&color=4ab8a1)](https://github.com/itstack-naive-chat/itstack-naive-chat-server) [<img src="https://itstack.org/_media/onlinebook.svg">](http://chat.itstack.org) [<img src="https://itstack.org/_media/wxbugstack.svg">](https://itstack.org/_media/qrcode.png?x-oss-process=style/may)

> 本项目是作者小傅哥使用技术栈```JavaFx```、```Netty4.x```、```SpringBoot```、```Mysql```等，搭建的仿桌面版微信聊天工程实现通信核心功能。如果本项目能为您提供帮助，请给予支持(关注、点赞、分享)！

> **作者：** 小傅哥，Java Developer，[:trophy: CSDN 博客专家](https://bugstack.blog.csdn.net)，[:bug: 虫洞 · 科技栈](https://bugstack.cn)

**学习链接：**

- [《Netty+JavaFx实战：仿桌面版微信聊天》](https://chat.itstack.org)
 
**赏个鸡腿🍗**

![](https://bugstack.cn/assets/images/tip.jpg)

----

## 一、简述

服务端同样使用Netty4.x作为socket的通信框架，同时在服务端使用Layui作为管理后台的页面，并且我们的服务端采用偏向于DDD领域驱动设计的方式与Netty集合，以此来达到我们的框架结构整洁干净易于扩展。同时我们的通信协议也是在服务端进行定义的，并对外提供可引入的Jar包，这样来保证客户端与服务端共同协议下进行通信。

## 二、Maven配置

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>2.1.7.RELEASE</version>
  <relativePath/> <!-- lookup parent from repository -->
</parent>
```

## 三、系统架构

![](https://chat.itstack.org/assets/img/2020/chat/wechat-2.1-00.png)

- 应用层{application}

	- 应用服务位于应用层。用来表述应用和用户行为，负责服务的组合、编排和转发，负责处理业务用例的执行顺序以及结果的拼装。
	- 应用层的服务包括应用服务和领域事件相关服务。
	- 应用服务可对微服务内的领域服务以及微服务外的应用服务进行组合和编排，或者对基础层如文件、缓存等数据直接操作形成应用服务，对外提供粗粒度的服务。
	- 领域事件服务包括两类：领域事件的发布和订阅。通过事件总线和消息队列实现异步数据传输，实现微服务之间的解耦。

- 领域层{domain}

	- 领域服务位于领域层，为完成领域中跨实体或值对象的操作转换而封装的服务，领域服务以与实体和值对象相同的方式参与实施过程。
	- 领域服务对同一个实体的一个或多个方法进行组合和封装，或对多个不同实体的操作进行组合或编排，对外暴露成领域服务。领域服务封装了核心的业务逻辑。实体自身的行为在实体类内部实现，向上封装成领域服务暴露。
	- 为隐藏领域层的业务逻辑实现，所有领域方法和服务等均须通过领域服务对外暴露。
	- 为实现微服务内聚合之间的解耦，原则上禁止跨聚合的领域服务调用和跨聚合的数据相互关联。

- 基础层{infrastructrue}

	- 基础服务位于基础层。为各层提供资源服务（如数据库、缓存等），实现各层的解耦，降低外部资源变化对业务逻辑的影响。
	- 基础服务主要为仓储服务，通过依赖反转的方式为各层提供基础资源服务，领域服务和应用服务调用仓储服务接口，利用仓储实现持久化数据对象或直接访问基础资源。

- 接口层{interfaces}
	
	- 接口服务位于用户接口层，用于处理用户发送的Restful请求和解析用户输入的配置文件等，并将信息传递给应用层。
	
----

如果本项目能为您提供帮助，请给予支持(关注、点赞、分享)！