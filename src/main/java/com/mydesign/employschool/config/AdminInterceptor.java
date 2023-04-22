package com.mydesign.employschool.config;

import com.mydesign.employschool.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User)request.getSession().getAttribute("userName");
        if (user != null && user.getRoleId() == 1){
            return true;
        }
        return false;
    }
}
