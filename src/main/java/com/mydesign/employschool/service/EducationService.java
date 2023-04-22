package com.mydesign.employschool.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mydesign.employschool.entity.Education;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mydesign.employschool.entity.Project;

import java.util.List;

/**
 * <p>
 * 教育经历 服务类
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
public interface EducationService extends IService<Education> {
    List<Education> listByUserId(Integer userId);
}
