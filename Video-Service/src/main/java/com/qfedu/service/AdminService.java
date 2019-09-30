package com.qfedu.service;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/17 16:11
 */
public interface AdminService {
    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    boolean isLogin(String username, String password);

}
