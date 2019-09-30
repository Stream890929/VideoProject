package com.qfedu.mapper;

import com.qfedu.entity.User;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/24 16:27
 */
public interface UserMapper {
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
    int insertUser(User user);

    /**
     * 根据id修改用户资料
     * @param user
     * @return
     */
    int updateUserById(User user);

    /**
     * 验证用户输入的邮箱和密码是否正确
     * @param user
     * @return
     */
    int getCountUser(User user);

    /**
     * 根据邮箱获取用户信息
     * @param email 邮箱
     * @return
     */
    User getUserByEmail(String email);

    /**
     * 修改用户头像
     * @param user
     * @return
     */
    int updateBySelective(User user);

}
