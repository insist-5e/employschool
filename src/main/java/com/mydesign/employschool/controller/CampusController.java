package com.mydesign.employschool.controller;


import com.mydesign.employschool.common.Result;
import com.mydesign.employschool.entity.Campus;
import com.mydesign.employschool.entity.User;
import com.mydesign.employschool.service.CampusService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 获奖情况 前端控制器
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/campus")
public class CampusController {
    @Resource
    HttpSession httpSession;
    @Resource
    CampusService campusService;
    @PostMapping("/addCampus")
    @ApiOperation(value="添加在校获奖情况信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Result addCam(@RequestBody Campus campus){
        User user = (User) httpSession.getAttribute("userName");
        campus.setUserId(user.getId());
        campusService.save(campus);
        return Result.success(null);
    }
    @PostMapping("/updateCampus")
    @ApiOperation(value="更新在校获奖情况信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Result updateCam(@RequestBody Campus campus){
        campusService.updateById(campus);
        return Result.success(null);
    }

    @GetMapping("/showCam")
    @ApiOperation(value="查看在校获奖情况信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Result showCam(){
        User user = (User) httpSession.getAttribute("userName");
        List<Campus> campusList = campusService.listByUserId(user.getId());
        return Result.success(campusList);
    }
    @GetMapping("/deleteCam")
    @ApiOperation(value="删除在校获奖情况信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Result delete(Integer id){
        campusService.removeById(id);
        return Result.success(null);
    }

    @GetMapping("/admin/showAll")
    public Result showAll(Integer userId){
        List<Campus> campusList = campusService.listByUserId(userId);
        return Result.success(campusList);
    }
}
