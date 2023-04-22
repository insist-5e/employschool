package com.mydesign.employschool.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mydesign.employschool.entity.Campus;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mydesign.employschool.entity.Project;

import java.util.List;

/**
 * <p>
 * 获奖情况 服务类
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
public interface CampusService extends IService<Campus> {
    List<Campus> listByUserId(Integer userId);
}
