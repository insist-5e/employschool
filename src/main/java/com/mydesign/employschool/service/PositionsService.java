package com.mydesign.employschool.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.employschool.entity.Positions;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 职位表 服务类
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
public interface PositionsService extends IService<Positions> {
    List<Positions> selectByUserId(Integer userId);

    Page<Positions> selectLikeByKeyWord(String classify, String name, Integer page, Integer size);
}
