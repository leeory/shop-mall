package com.shopmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;

    private String userName;

    private String password;

    private String phone;

    private Boolean deleteFlag;

    private Date createTime;

    private Date updateTime;

}