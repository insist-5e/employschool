package com.mydesign.employschool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.employschool.entity.Resume;
import com.mydesign.employschool.mapper.ResumeMapper;
import com.mydesign.employschool.service.ResumeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 简历 服务实现类
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
@Service
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements ResumeService {

    @Resource
    ResumeMapper resumeMapper;
    @Override
    public Page<Resume> showAll(Integer page, Integer size) {
        return (Page<Resume>) resumeMapper.selectPage(new Page<>(page, size),new QueryWrapper<>());
    }
}
