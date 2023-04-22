package com.mydesign.employschool.controller;


import com.mydesign.employschool.common.Result;
import com.mydesign.employschool.entity.Education;
import com.mydesign.employschool.entity.User;
import com.mydesign.employschool.service.EducationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 教育经历 前端控制器
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/edu")
public class EducationController {
    @Resource
    HttpSession httpSession;
    @Resource
    EducationService educationService;
    @PostMapping("/updateById")
    @ApiOperation(value="根据主键更新学历信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object updateEdu(@RequestBody Education education){
        educationService.updateById(education);
        return Result.success(null);
    }

    @PostMapping("/add")
    @ApiOperation(value="添加学历信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Result addEdu(@RequestBody Education education){
        User user = (User) httpSession.getAttribute("userName");
        education.setUserId(user.getId());
        educationService.save(education);
        return Result.success(null);
    }

    @GetMapping("/show")
    @ApiOperation(value="查看个人学历信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Result show(){
        User user = (User) httpSession.getAttribute("userName");
        List<Education> educationList = educationService.listByUserId(user.getId());
        return Result.success(educationList);
    }

    @GetMapping("/admin/showAll")
    public Result showOne(Integer userId){
        List<Education> educationList = educationService.listByUserId(userId);
        return Result.success(educationList);
    }
}
