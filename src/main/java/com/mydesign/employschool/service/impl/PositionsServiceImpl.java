package com.mydesign.employschool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.employschool.entity.Positions;
import com.mydesign.employschool.entity.Resume;
import com.mydesign.employschool.mapper.PositionsMapper;
import com.mydesign.employschool.service.PositionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mydesign.employschool.service.ResumeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 职位表 服务实现类
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
@Service
public class PositionsServiceImpl extends ServiceImpl<PositionsMapper, Positions> implements PositionsService {

    @Resource
    PositionsMapper positionsMapper;
    @Resource
    ResumeService resumeService;
    @Override
    public List<Positions> selectByUserId(Integer userId) {
        QueryWrapper<Resume> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Resume> resumes = resumeService.list(queryWrapper);
        List<Integer> integers = new ArrayList<>();
        for(Resume resume:resumes){
            integers.add(resume.getPositionsId());
        }
        return (List<Positions>) listByIds(integers);
    }

    @Override
    public Page<Positions> selectLikeByKeyWord(String classify, String name, Integer page, Integer size) {
        QueryWrapper<Positions> queryWrapper = new QueryWrapper<>();
        if(classify != null) queryWrapper.like("classify",classify);
        if(name != null) queryWrapper.like("positions_name", name);
        return (Page<Positions>) positionsMapper.selectPage(new Page<>(page, size), queryWrapper);
    }
}
