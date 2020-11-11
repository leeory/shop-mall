package com.shopmall.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ly
 * @Description:  公共返回结果集
 * @Date: 2019/11/3 14:52
 * @Title:
 * @param:
 * @return:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {


    private String code;

    private String message;

    private T data;

    /**
     * 成功时候的调用
     * */
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    /**
     * 异常时候的调用
     * */
    public static <T> Result<T> error(ResultEnum re){
        return new Result<T>(re);
    }


    private Result(T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getName();
        this.data = data;
    }

    private Result(ResultEnum re) {
        this.code = re.getCode();
        this.message = re.getName();
        this.data = null;
    }




}
