package com.mydesign.employschool.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.employschool.common.Result;
import com.mydesign.employschool.entity.Collection;
import com.mydesign.employschool.entity.Positions;
import com.mydesign.employschool.entity.User;
import com.mydesign.employschool.service.CollectionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 收藏 前端控制器
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Resource
    HttpSession httpSession;
    @Resource
    CollectionService collectionService;
    @PostMapping("/add")
    @ApiOperation(value="收藏")
    public Result addCollection(Integer positionsId){
        User user = (User) httpSession.getAttribute("userName");
        Collection collection = new Collection();
        collection.setUserId(user.getId());
        collection.setPositionsId(positionsId);
        collectionService.save(collection);
        return Result.success(null);
    }
    @PostMapping("/delete")
    @ApiOperation(value="取消收藏")
    public Result deleteCollection(Integer positionsId){
        User user = (User) httpSession.getAttribute("userName");
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        queryWrapper.eq("positions_id", positionsId);
        collectionService.remove(queryWrapper);
        return Result.success(null);
    }

    @GetMapping("/showAll")
    @ApiOperation(value="查看我的收藏")
    public Result showAllCollection(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "5")Integer size){
        User user = (User) httpSession.getAttribute("userName");
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        Page<Positions> positionsPage = collectionService.showAll(page, size, queryWrapper);
        return Result.success(positionsPage);
    }
}
