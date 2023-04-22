package com.mydesign.employschool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mydesign.employschool.entity.Campus;
import com.mydesign.employschool.entity.Project;
import com.mydesign.employschool.mapper.CampusMapper;
import com.mydesign.employschool.service.CampusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 获奖情况 服务实现类
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
@Service
public class CampusServiceImpl extends ServiceImpl<CampusMapper, Campus> implements CampusService {
    @Override
    public List<Campus> listByUserId(Integer userId) {
        QueryWrapper<Campus> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return list(queryWrapper);
    }
}
