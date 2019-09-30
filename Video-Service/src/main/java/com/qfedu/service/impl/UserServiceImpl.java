package com.qfedu.service.impl;

import com.qfedu.entity.User;
import com.qfedu.mapper.UserMapper;
import com.qfedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/24 16:25
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 注册验证
     *
     * @param email 邮箱
     * @return
     */
    @Override
    public int validateEmail(String email) {
        return userMapper.validateEmail(email);
    }

    @Override
    public int register(User user) {
        return userMapper.insertUser (user);
    }

    @Override
    public int getCountUser(User user) {
        return userMapper.getCountUser (user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail (email);
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @Override
    public int updateUserById(User user) {
        return userMapper.updateBySelective (user);
    }

    @Override
    public int updateBySelective(User user) {
        return userMapper.updateBySelective(user);
    }

}
