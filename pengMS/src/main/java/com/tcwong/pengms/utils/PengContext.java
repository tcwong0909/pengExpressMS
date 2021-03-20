package com.tcwong.pengms.utils;

import com.tcwong.pengms.model.User;

/**
 * Description 可变参数上下文
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
public class PengContext {
    /** 登陆人 */
    private static ThreadLocal<User> USER = new ThreadLocal<>();

    /**
     * Description 设置登陆用户
     *
     * @param user 登陆参数
     * @return
     * @author tcwong
     * @date 2021/3/20
     */
    public static void setUser(User user) {
        USER.set(user);
    }

    /**
     * Description 获取登陆用户
     *
     * @return 登陆用户
     * @author tcwong
     * @date 2021/3/20
     */
    public static User getUser() {
        return USER.get();
    }

    /**
     * Description 清楚登陆用户
     *
     * @author tcwong
     * @date 2021/3/20
     */
    public static void removeUser() {
        USER.remove();
    }
}
