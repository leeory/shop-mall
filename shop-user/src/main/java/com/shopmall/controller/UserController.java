package com.shopmall.controller;

import com.shopmall.common.Result;
import com.shopmall.common.ResultEnum;
import com.shopmall.entity.User;
import com.shopmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("addUser")
    public Result addUser(User user){
        int row = userService.saveUser(user);
        return row == 1? Result.success(""):Result.error(ResultEnum.BUSINESS_ERROR);
    }

    @GetMapping("queryUserById")
    public Result queryUserById(Long  userId){
        User user = userService.queryById(userId);
        return  Result.success(user);
    }

}
