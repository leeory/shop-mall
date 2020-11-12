package com.shopmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;

    private Long userId;

    private String userName;

    private Long productId;

    private String productName;

    private Integer number;

    private Boolean deleteFlag;

    private Date createTime;

    private Date updateTime;

}