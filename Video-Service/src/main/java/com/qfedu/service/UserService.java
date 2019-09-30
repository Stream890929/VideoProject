package com.qfedu.service;

import com.qfedu.entity.User;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/24 16:23
 */
public interface UserService {
    /**
     * 验证邮箱是否存在
     * @param email 邮箱
     * @return
     */
    int validateEmail(String email);

    /**
     * 用户注册
     * @param user
     * @return
     */
    int register(User user);

    /**
     * 用户登录验证
     * @param user
     * @return
     */
    int getCountUser(User user);

    /**
     * 根据邮箱获取用户
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     * 根据用户id修改用户资料
     * @param user
     * @return
     */
    int updateUserById(User user);

    /**
     * 修改用户头像图片
     * @param user
     * @return
     */
    int updateBySelective(User user);


}
