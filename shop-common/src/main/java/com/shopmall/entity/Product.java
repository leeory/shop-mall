package com.shopmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;

    private String productName;

    private Integer stock;

    private Double price;

    private Boolean deleteFlag;

    private Date createTime;

    private Date updateTime;


}