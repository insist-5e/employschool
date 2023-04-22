package com.mydesign.employschool.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.employschool.entity.Resume;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 简历 服务类
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
public interface ResumeService extends IService<Resume> {
    Page<Resume> showAll(Integer page, Integer size);
}
