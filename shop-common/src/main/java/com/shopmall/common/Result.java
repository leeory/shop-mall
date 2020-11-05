package com.shopmall.common;

/**
 * @author: ly
 * @Description:  公共返回结果集
 * @Date: 2019/11/3 14:52
 * @Title:
 * @param:
 * @return:
 */
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



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
