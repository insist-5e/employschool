package com.mydesign.employschool.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.employschool.common.Result;
import com.mydesign.employschool.entity.Positions;
import com.mydesign.employschool.entity.User;
import com.mydesign.employschool.mapper.PositionsMapper;
import com.mydesign.employschool.service.PositionsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 职位表 前端控制器
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/pos")
public class PositionsController {
    @Resource
    HttpSession httpSession;
    @Resource
    PositionsMapper positionsMapper;
    @Resource
    PositionsService positionsService;

    @GetMapping("/likeByCondition")
    @ApiOperation(value="臭胖啊，这个是模糊查询（这些参数不一定都要输入的哦,可以用于搜索框）pName：岗位名，pClassify：岗位分类",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Result checkLikeCondition( String classify,String name, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size){
        Page<Positions> positionsPage = positionsService.selectLikeByKeyWord(classify, name, page, size);
        return Result.success(positionsPage);
    }

    @PostMapping("/admin/add")
    public Result addPos(@RequestBody Positions pos){
        positionsService.save(pos);
        return Result.success(null);
    }

    @PostMapping("/admin/delete")
    public Result deletePos(Integer id){
        positionsService.removeById(id);
        return Result.success(null);
    }

    @GetMapping("/showAll")
    public Result showAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size) {
        Page<Positions> positionsList = (Page<Positions>) positionsMapper.selectPage(new Page<>(page, size), new QueryWrapper<>());
        return Result.success(positionsList);
    }

    @PostMapping("/admin/update")
    public Result updatePos(@RequestBody Positions positions) {
        positionsService.updateById(positions);
        return Result.success(null);
    }

    @PostMapping("/admin/selectOne")
    public Result selectById(Integer id){
        Positions positions = positionsMapper.selectById(id);
        return Result.success(positions);
    }
}
