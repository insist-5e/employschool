package com.mydesign.employschool.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.employschool.common.Result;
import com.mydesign.employschool.entity.Positions;
import com.mydesign.employschool.entity.Resume;
import com.mydesign.employschool.entity.User;
import com.mydesign.employschool.mapper.PositionsMapper;
import com.mydesign.employschool.service.PositionsService;
import com.mydesign.employschool.service.ResumeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 简历 前端控制器
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Resource
    HttpSession httpSession;
    @Resource
    ResumeService resumeService;
    @Resource
    PositionsMapper positionsMapper;
    @Resource
    PositionsService positionsService;
    @PostMapping("/add")
    @ApiOperation(value="投递简历")
    public Result addResume(@RequestBody Resume resume){
        User user = (User)httpSession.getAttribute("userName");
        resume.setUserId(user.getId());
        resumeService.save(resume);
        return Result.success(null);
    }
    @PostMapping("/delete")
    @ApiOperation(value="撤销投递简历")
    public Result deleteResume(Integer positionsId){
        User user = (User)httpSession.getAttribute("userName");
        QueryWrapper<Resume> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        queryWrapper.eq("positions_id", positionsId);
        resumeService.remove(queryWrapper);
        return Result.success(null);
    }
    @GetMapping("/show")
    @ApiOperation(value="展示已经投递简历信息")
    public Result show(){
        User user = (User)httpSession.getAttribute("userName");
        List<Positions> positions = positionsService.selectByUserId(user.getId());
        return Result.success(positions);
    }

    @GetMapping("/admin/showAll")
    public Result showAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5")Integer size){
        Page<Resume> resumes = resumeService.showAll(page, size);
        for(Resume resume: resumes.getRecords()){
            Positions positions = positionsMapper.selectById(resume.getPositionsId());
            resume.setPositions(positions);
        }
        return Result.success(resumes);
    }
}
