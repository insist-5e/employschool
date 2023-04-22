package com.mydesign.employschool.controller;

import com.google.code.kaptcha.Producer;
import com.mydesign.employschool.common.Result;
import com.mydesign.employschool.entity.User;
import com.mydesign.employschool.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    HttpSession httpSession;
    @Resource
    private Producer producer;
    @Resource
    UserService userService;
    @PostMapping("/register")
    @ApiOperation(value="注册")
    public Result register(@RequestBody User user){
        boolean flag = userService.register(user);
        if(flag) return Result.success(null);
        return Result.fail(null);
    }

    @PostMapping("/login")
    @ApiOperation(value="登录")
    public Result login(String name, String password, String checkCode){
        String code = (String) httpSession.getAttribute("checkCode");
        if(!checkCode.equalsIgnoreCase(code)) return Result.fail(null);
        User user = userService.login(name, password);
        if(user != null){
            httpSession.setAttribute("userName",user);
            return Result.success(user);
        }
        return Result.fail(null);
    }

    @GetMapping("/checkCode")
    @ApiOperation(value="接收验证码" )
    public void getCheckCode(HttpServletResponse resp, HttpServletRequest req){
        resp.setHeader("pragma","no-cache");
        resp.setHeader("cache-control","no-cache");
        resp.setHeader("expires","0");
        String code = producer.createText();
        BufferedImage image = producer.createImage(code);
        req.getSession().setAttribute("checkCode",code);
        resp.setContentType("image/png");
        try {
            ServletOutputStream outputStream = resp.getOutputStream();
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("响应验证码失败");
        }
    }

    @GetMapping("/logout")
    @ApiOperation(value="注销登录" )
    public Result logout(){
        httpSession.removeAttribute("userName");
        return Result.success(null);
    }

    @GetMapping("/admin/showAll")
    public Result showAll(){
        return Result.success(userService.list());
    }

    @PostMapping("/admin/update")
    public Result updateUser(@RequestBody User user){
        return Result.success(userService.updateById(user));
    }
}
