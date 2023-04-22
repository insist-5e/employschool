package com.mydesign.employschool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mydesign.employschool.entity.Education;
import com.mydesign.employschool.entity.Project;
import com.mydesign.employschool.mapper.EducationMapper;
import com.mydesign.employschool.service.EducationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 教育经历 服务实现类
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
@Service
public class EducationServiceImpl extends ServiceImpl<EducationMapper, Education> implements EducationService {
    @Override
    public List<Education> listByUserId(Integer userId) {
        QueryWrapper<Education> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return list(queryWrapper);
    }
}
