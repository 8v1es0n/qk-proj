package com.itheima.common.utils;


public class UserHolder {

    // 当前登录用户
    private static final ThreadLocal<Integer> CURRENT_USER = new ThreadLocal<>();

    /**
     * 设置当前登录用户
     */
    public static void setCurrentUser(Integer userId) {
        CURRENT_USER.set(userId);
    }

    /**
     * 获取当前登录用户
     * @return  返回获取到的结果
     */
    public static Integer getCurrentUser() {
        return CURRENT_USER.get();
    }

    /**
     * 移除当前登录用户
     */
    public static void removeCurrentUser() {
        CURRENT_USER.remove();
    }
}
