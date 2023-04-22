package com.mydesign.employschool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mydesign.employschool.entity.Education;
import com.mydesign.employschool.entity.Information;
import com.mydesign.employschool.mapper.InformationMapper;
import com.mydesign.employschool.service.InformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements InformationService {
    @Override
    public List<Information> listByUserId(Integer userId) {
        QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return list(queryWrapper);
    }
}
