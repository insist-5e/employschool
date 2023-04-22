package com.mydesign.employschool.service;

import com.mydesign.employschool.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
public interface UserService extends IService<User> {
    boolean register(User user);

    User login(String name, String password);
}
