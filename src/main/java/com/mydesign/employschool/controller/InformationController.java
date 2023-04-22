package com.mydesign.employschool.controller;


import com.alibaba.fastjson.JSONObject;
import com.mydesign.employschool.common.Result;
import com.mydesign.employschool.entity.Information;
import com.mydesign.employschool.entity.User;
import com.mydesign.employschool.service.InformationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/info")
public class InformationController {
    @Resource
    HttpSession httpSession;
    @Resource
    InformationService informationService;

    @PostMapping("/add")
    public Result addInfo(@RequestBody Information information){
        informationService.save(information);
        return Result.success(null);
    }
    @PostMapping("/update")
    @ApiOperation(value="更新基本信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Result updateInfo(@RequestBody Information info){
        User user = (User) httpSession.getAttribute("userName");
        info.setUserId(user.getId());
        informationService.saveOrUpdate(info);
        return Result.success(null);
    }

    @PostMapping("/uploadPic")
    @ApiOperation(value="上传照片",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Result uploadUserPic(@RequestParam("photo") MultipartFile upFile) {
        String fileName = System.currentTimeMillis() + upFile.getOriginalFilename();
        String filePath = Thread.currentThread().getContextClassLoader().getResource("/").getPath() + "static/img";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        File dest = new File(filePath + "/" + fileName);
        String storePath = "/static/img/" + fileName;
        try {
            upFile.transferTo(dest.getAbsoluteFile());// 用来把 MultipartFile 转换换成 File
            User user = (User) httpSession.getAttribute("userName");
            List<Information> informationList = informationService.listByUserId(user.getId());
            Information information = informationList.get(0);
            information.setPic(storePath);
            informationService.updateById(information);
            return Result.success(storePath);
        } catch (IOException e) {
            return Result.fail(null);
        }
    }
    @PostMapping("/uploadResume")
    @ApiOperation(value="上传附件",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Result uploadResumeFile(@RequestParam("file") MultipartFile upFile) {
        String fileName = System.currentTimeMillis() + upFile.getOriginalFilename();
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "static/resume";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        File dest = new File(filePath + "/" + fileName);
        String storePath = "static/resume/" + fileName;
        try {
            System.out.println(dest.getAbsolutePath());
            upFile.transferTo(dest.getAbsoluteFile());
            User user = (User) httpSession.getAttribute("userName");
            List<Information> informationList = informationService.listByUserId(user.getId());
            Information information = informationList.get(0);
            information.setFile(storePath);
            informationService.updateById(information);
            return Result.success(storePath);
        } catch (IOException e) {
            return Result.fail(null);
        }
    }

    @GetMapping("/showAll")
    @ApiOperation(value="展示基本信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Result showAll(){
        User user = (User) httpSession.getAttribute("userName");
        List<Information> informationList = informationService.listByUserId(user.getId());
        return Result.success(informationList.get(0));
    }

}
