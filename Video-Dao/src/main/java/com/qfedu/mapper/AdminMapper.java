package com.qfedu.mapper;

import com.qfedu.entity.Admin;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/17 16:13
 */
public interface AdminMapper {
    /**
     * 查询用户名和密码
     * @param admin 用户实体类
     * @return
     */
    int selectAdminByUsernameAndPassword(Admin admin);
}
