package com.qfedu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// ctrl + o  提示能够重写哪些父类的方法
public class LoginInterceptor implements HandlerInterceptor {


    // 在handler(处理器，就是Controller中的方法) 执行前，如果返回true,继续访问你的handler,否则终止访问
    // 一般用于登录和权限校验
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        // http://localhost:8888/videoPlus/admin/showLogin
        Object username = request.getSession().getAttribute("USERNAME");
        Object email = request.getSession().getAttribute("userAccount");
        if(uri.contains("/beforeVideo/")|| uri.contains("/user/showMyProfile")){
             if(email == null ){
                 response.sendRedirect(request.getContextPath()+"/index.jsp");
                 return false;
             }
        }

        if(uri.contains("/video/")){
            if(username == null ){
                response.sendRedirect(request.getContextPath()+"/admin/showLogin");
                return false;
            }
        }

        return true;
    }

    // 执行时机：handler执行后，页面返回前 可以进行偷梁换柱
    // 用户给页面追加一个特殊信息
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     *
     * 处理器执行完，并且页面也返回给用户之后
     * 用户记录用户的行为日志
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
