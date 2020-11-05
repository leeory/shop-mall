package com.shopmall.service;

import com.shopmall.entity.User;

public interface UserService {

    int saveUser(User user);

    User queryById(Long userId);
}
