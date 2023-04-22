package com.mydesign.employschool.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.employschool.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mydesign.employschool.entity.Positions;

import java.util.List;

/**
 * <p>
 * 收藏 服务类
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
public interface CollectionService extends IService<Collection> {
    Page<Positions> showAll(Integer page, Integer size, QueryWrapper<Collection> queryWrapper);
}
