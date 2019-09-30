package com.qfedu.controller;

import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import utils.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/24 14:16
 */
@Controller
@RequestMapping("/user")
@Api(value = "用户操作", tags = "用户操作接口，例如登录、注册等")
public class UserController {
    @Autowired
    UserService userService;

    @Value ("${IMG_PATH}")
    String imgPath;
    @Value ("${IMG_URL}")
    String imgUrl;


    /**
     * 检验邮箱是否存在
     *
     * @param email 邮箱
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/validateEmail", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "接口名", notes = "接口描述", httpMethod = "GET，POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String")
    })
    public String validateEmail(String email) {
        int result = userService.validateEmail (email);
        if (result == 0) {
            return "success";
        }
        return "fail";
    }

    /**
     * 用户注册
     *
     * @param user    用户
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertUser", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "用户注册", notes = "注册新用户", httpMethod = "GET,POST")
    public String insertUser(HttpSession session, User user) {
        user.setPassword (Md5Utils.getMd5Str (user.getPassword ()));
        int result = userService.register (user);
        if (result > 0) {
            session.setAttribute ("userAccount", user.getEmail ());
            return "success";
        }
        return "fail";
    }

    /**
     * 用户登录
     *
     * @param user    用户
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginUser", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "loginUser", notes = "用户登录方法", httpMethod = "GET,POST")
    public String loginUser(HttpSession session, User user) {
        user.setPassword (Md5Utils.getMd5Str (user.getPassword ()));
        int result = userService.getCountUser (user);
        if (result > 0) {
            session.setAttribute ("userAccount", user.getEmail ());
            return "success";
        }
        return "fail";
    }

    /**
     * 用户退出方法
     * @param session
     * @return
     */
    @RequestMapping(value = "/logOut",method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "logOut", notes = "用户登出方法", httpMethod = "GET,POST")
    public String logOut(HttpSession session){
        session.removeAttribute ("userAccount");
        return "../../index";
    }

    /**
     * 个人中心
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/showMyProfile", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "showMyProfile", notes = "跳转至个人中心", httpMethod = "GET,POST")
    public String showMyProfile(HttpSession session, Model model) {
        // 获取用户邮箱
        String email = (String) session.getAttribute ("userAccount");
        User user = userService.getUserByEmail (email);
        if (user.getImgurl () != null) {
            // 获取用户头像
            user.setImgurl (imgUrl + user.getImgurl ());
        }
        model.addAttribute ("user", user);
        return "before/my_profile";
    }

    /**
     * 跳转至修改资料页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/changeProfile", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "changeProfile", notes = "跳转至修改个人资料页面", httpMethod = "GET,POST")
    public String changeProfile(HttpSession session, Model model) {
        // 获取用户邮箱
        String email = (String) session.getAttribute ("userAccount");
        User user = userService.getUserByEmail (email);
        if (user.getImgurl () != null) {
            // 获取用户头像
            user.setImgurl (imgUrl+ user.getImgurl ());
        }
        model.addAttribute ("user", user);
        return "before/change_profile";
    }

    /**
     * 修改个人信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUser", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "updateUser", notes = "修改个人资料", httpMethod = "GET,POST")
    public String updateUser(User user) {
        userService.updateUserById (user);
        return "redirect:/user/showMyProfile";
    }

    /**
     * 跳转至修改头像页面
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/changeAvatar", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "changeAvatar", notes = "跳转至修改头像页面", httpMethod = "GET,POST")
    public String changeAvatar(HttpSession session, Model model) {
        // 获取用户邮箱
        String email = (String) session.getAttribute ("userAccount");
        User user = userService.getUserByEmail (email);
        if (user.getImgurl () != null) {
            // 获取用户头像
            user.setImgurl (imgUrl + user.getImgurl ());
        }
        model.addAttribute ("user", user);
        return "before/change_avatar";
    }

    /**
     * 修改用户头像
     *
     * @param session
     * @param image_file 头像文件
     * @return
     */
    @RequestMapping(value = "/upLoadImage", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "upLoadImage", notes = "修改用户头像", httpMethod = "GET,POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "image_file", value = "用户头像", required = true, dataType = "MultipartFile"),
            @ApiImplicitParam(name = "x1", value = "图片坐标", required = true, dataType = "String"),
            @ApiImplicitParam(name = "x2", value = "图片坐标", required = true, dataType = "String"),
            @ApiImplicitParam(name = "y1", value = "图片坐标", required = true, dataType = "String"),
            @ApiImplicitParam(name = "y2", value = "图片坐标", required = true, dataType = "String")
    })
    public String upLoadImage(HttpSession session, MultipartFile image_file, String x1, String x2, String y1, String y2) {
        // 文件名-》截取后缀-》文件名称加密-》上传位置->上传方法
        String oldName = image_file.getOriginalFilename ();
        String suffix = oldName.substring (oldName.lastIndexOf ("."));
        String newFileName = UUIDUtils.getUUID () + suffix;
        String fileDir = imgPath;

        File file = new File (fileDir, newFileName);
        try {
            image_file.transferTo (file);
        } catch (IOException e) {
            e.printStackTrace ();
        }

        // 转换头像图片坐标数据类型
        float _x1 = Float.valueOf (x1);
        float _x2 = Float.valueOf (x2);
        float _y1 = Float.valueOf (y1);
        float _y2 = Float.valueOf (y2);

        // 使用坐标截取上传的图片
        ImageCut imageCut = new ImageCut ();
        imageCut.cutImage (fileDir + "/" + newFileName, (int) _x1, (int) _y1, (int) (_x2 - _x1), (int) (_y2 - _y1));

        // 获取用户邮箱
        String email = (String) session.getAttribute ("userAccount");
        // 更新数据库
        User user = new User ();
        user.setEmail (email);
        user.setImgurl (newFileName);
        int result = userService.updateBySelective (user);
        return "redirect:/user/showMyProfile";
    }

    /**
     * 跳转至修改密码页面
     *
     * @return
     */
    @RequestMapping(value = "/passwordSafe",method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "passwordSafe", notes = "跳转至修改密码页面", httpMethod = "GET,POST")
    public String passwordSafe(HttpSession session, Model model) {
        // 获取用户邮箱
        String email = (String) session.getAttribute ("userAccount");
        User user = userService.getUserByEmail (email);
        if (user.getImgurl () != null) {
            // 获取用户头像
            user.setImgurl (imgUrl + user.getImgurl ());
        }
        model.addAttribute ("user", user);
        return "before/password_safe";
    }

    /**
     * 验证原始密码是否正确
     *
     */
    @ResponseBody
    @RequestMapping(value = "/validatePassword",method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "validatePassword", notes = "验证旧密码是否正确", httpMethod = "GET,POST")
    public String validatePassword(HttpSession session,String password) {
        // 获取用户邮箱
        String email = (String) session.getAttribute ("userAccount");
        User user = new User ();
        user.setEmail (email);

        // 输入框密码加密转换
        user.setPassword (Md5Utils.getMd5Str (password));
        int result = userService.getCountUser (user);

        if (result > 0) {
            return "success";
        }
        return "fail";
    }

    /**
     * 修改密码方法
     * @param
     * @return
     */
    @RequestMapping(value = "/updatePassword",method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "updatePassword", notes = "修改用户密码方法", httpMethod = "GET,POST")
    public String updatePassword(HttpSession session,String newPassword) {
        // 获取用户邮箱
        String email = (String) session.getAttribute ("userAccount");
        User user = new User ();
        user.setEmail (email);
        // 输入框密码加密转换
        user.setPassword (Md5Utils.getMd5Str (newPassword));
        int result = userService.updateUserById (user);
        session.removeAttribute ("userAccount");
        return "../../index";
    }

    /**
     * 跳转至忘记密码页面
     * @return
     */
    @RequestMapping(value = "/forgetPassword")
    public String forgetPassword(){
        return "before/forget_password";
    }

    /**
     * 发送验证码邮件
     * @param session
     * @param email 用户邮箱
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendEmail")
    public String sendEmail(HttpSession session,String email){
        String uuid = UUIDUtils.getUUID ();
        String substring = uuid.substring (0,6);
        // 发送邮件
        MailUtils.sendMail (email,substring,"验证码");
        session.setAttribute ("substring",substring);
        return "success";
    }

    /**
     * 跳转至重置密码页面
     * @param session
     * @return
     */
    @RequestMapping(value = "/validateEmailCode")
    public String validateEmailCode(HttpSession session){
        String email = (String) session.getAttribute ("userAccount");
        System.out.println (email);
        return "before/reset_password";
    }

    /**
     * 修改用户密码
     * @param session
     * @param password
     * @return
     */
    @RequestMapping(value = "/resetPassword")
    public String resetPassword(HttpSession session,String password){
        // 获取用户邮箱
        String email = (String) session.getAttribute ("userAccount");
        User user = new User ();
        user.setEmail (email);
        // 输入框密码加密转换
        user.setPassword (Md5Utils.getMd5Str (password));
        int result = userService.updateUserById (user);

        return "../../index";
    }

}
