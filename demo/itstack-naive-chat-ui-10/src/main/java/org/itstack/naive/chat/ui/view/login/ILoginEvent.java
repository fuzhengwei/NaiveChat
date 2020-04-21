package org.itstack.naive.chat.ui.view.login;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 *
 * 登陆窗口实现，外部给予实现
 */
public interface ILoginEvent {

    /**
     * 登陆验证
     * @param userId        用户ID
     * @param userPassword  用户密码
     */
    void doLoginCheck(String userId, String userPassword);

}
