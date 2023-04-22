package com.mydesign.employschool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.employschool.entity.Collection;
import com.mydesign.employschool.entity.Positions;
import com.mydesign.employschool.mapper.CollectionMapper;
import com.mydesign.employschool.mapper.PositionsMapper;
import com.mydesign.employschool.service.CollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mydesign.employschool.service.PositionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 收藏 服务实现类
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {
    @Resource
    CollectionMapper collectionMapper;
    @Resource
    PositionsMapper positionsMapper;

    @Override
    public Page<Positions> showAll(Integer page, Integer size, QueryWrapper<Collection> queryWrapper) {
        List<Collection> collections = list(queryWrapper);
        List<Integer> integers = new ArrayList<>();
        for(Collection collection : collections){
            integers.add(collection.getPositionsId());
        }
        if(integers.isEmpty()) return new Page<>(page, size);
        QueryWrapper<Positions> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.in("id", integers);
        return (Page<Positions>) positionsMapper.selectPage(new Page<>(page, size), queryWrapper1);
    }
}
