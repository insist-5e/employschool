package com.mydesign.employschool.controller;


import com.mydesign.employschool.common.Result;
import com.mydesign.employschool.entity.Project;
import com.mydesign.employschool.entity.User;
import com.mydesign.employschool.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 项目经验 前端控制器
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Resource
    HttpSession httpSession;
    @Resource
    ProjectService projectService;
    @PostMapping("/add")
    @ApiOperation(value="添加项目经历")
    public Result addProject(@RequestBody Project project){
        User user = (User) httpSession.getAttribute("userName");
        project.setUserId(user.getId());
        projectService.save(project);
        return Result.success(null);
    }
    @PostMapping("/update")
    @ApiOperation(value="更改项目经历信息")
    public Result updatePro(@RequestBody Project project){
        projectService.updateById(project);
        return Result.success(null);
    }
    @GetMapping("/delete")
    @ApiOperation(value="删除项目经历信息,根据项目id删除")
    public Result delete(@RequestParam("id") Integer id){
        projectService.removeById(id);
        return Result.success(null);
    }
    @GetMapping("/show")
    @ApiOperation(value="展示项目经历")
    public Result show(){
        User user = (User) httpSession.getAttribute("userName");
        List<Project> projectList = projectService.listByUserId(user.getId());
        return Result.success(projectList);
    }

    @GetMapping("/admin/showAll")
    public Result showAll(Integer userId){
        List<Project> projectList = projectService.listByUserId(userId);
        return Result.success(projectList);
    }
}
