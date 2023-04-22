package com.mydesign.employschool.service;

import com.mydesign.employschool.entity.Project;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 项目经验 服务类
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
public interface ProjectService extends IService<Project> {
    List<Project> listByUserId(Integer userId);
}
