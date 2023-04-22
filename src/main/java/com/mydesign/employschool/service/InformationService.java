package com.mydesign.employschool.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mydesign.employschool.entity.Education;
import com.mydesign.employschool.entity.Information;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
public interface InformationService extends IService<Information> {
    List<Information> listByUserId(Integer userId);
}
