package com.mydesign.employschool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mydesign.employschool.entity.User;
import com.mydesign.employschool.mapper.UserMapper;
import com.mydesign.employschool.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author liusiyu
 * @since 2023-04-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean register(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        List<User> list = list(queryWrapper);
        if(!list.isEmpty()){
            return false;
        }
        return save(user);
    }

    @Override
    public User login(String name, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", name);
        queryWrapper.eq("password", password);
        List<User> list = list(queryWrapper);
        if(list.isEmpty()) return null;
        return list.get(0);
    }
}
