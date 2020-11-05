package com.shopmall.service;

import com.shopmall.dao.UserMapper;
import com.shopmall.entity.User;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public int saveUser(User user) {
        user.setCreateTime(new Date());
        user.setDeleteFlag(false);
        return userMapper.insertSelective(user);
    }

    @Override
    public User queryById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
