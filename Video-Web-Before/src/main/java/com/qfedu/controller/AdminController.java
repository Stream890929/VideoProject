package com.qfedu.controller;

import com.qfedu.service.AdminService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/16 17:29
 */
@Controller
@RequestMapping("/admin")
@Api(value = "后台管理员操作", tags = "管理员操作接口，例如登录、登出等")
public class AdminController {
    @Autowired
    AdminService adminService;

    /**
     * 进入登录界面
     *
     * @return
     */
    @RequestMapping(value = "/showLogin", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "showLogin", notes = "跳转至登录界面", httpMethod = "GET,POST")
    public String showLogin() {
        return "behind/login";
    }

    /**
     * 登录方法
     *
     * @param username 用户名
     * @param password 密码
     * @param session  记住用户名
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "login", notes = "登录方法", httpMethod = "GET,POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    public String login(HttpSession session, String username, String password) {
        // 登录，用户名，密码验证
        if (adminService.isLogin (username, password)) {
            session.setAttribute ("USERNAME", username);
            return "success";
        }
        return "fail";
    }

    /**
     * 登出方法
     *
     * @param session 移除用户名
     * @return
     */
    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "logout", notes = "登出方法", httpMethod = "GET,POST")
    public String logout(HttpSession session) {
        session.removeAttribute ("USERNAME");
        return "behind/login";
    }

}
