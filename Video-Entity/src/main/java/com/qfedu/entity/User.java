package com.qfedu.entity;

import lombok.Data;

import java.util.Date;
/**
 * @author Stream
 */
@Data
public class User {
    private Integer id;
    private String email;
    private String phonenum;
    private String password;
    private String code;
    private String nickname;
    private String sex;
    private String birthday;
    private String address;
    private String imgurl;
    private Date createtime;

    public User() {
    }

    public User(Integer id, String email, String phonenum, String password, String code, String nickname, String sex, String birthday, String address, String imgurl, Date createtime) {
        this.id = id;
        this.email = email;
        this.phonenum = phonenum;
        this.password = password;
        this.code = code;
        this.nickname = nickname;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.imgurl = imgurl;
        this.createtime = createtime;
    }

}