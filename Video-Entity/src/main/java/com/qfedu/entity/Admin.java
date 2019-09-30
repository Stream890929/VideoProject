package com.qfedu.entity;

import lombok.Data;

/**
 * 用户实体类
 * @author Stream
 * @version 1.0
 * @date 2019/09/17 16:14
 */
@Data
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private String roles;

    public Admin() {
    }

    public Admin(Integer id, String username, String password, String roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

}
