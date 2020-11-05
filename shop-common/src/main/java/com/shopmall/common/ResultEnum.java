package com.shopmall.common;

public enum ResultEnum {

    SUCCESS("00","请求成功"),
    PARAM_ERROR("11","请求参数错误"),
    BUSINESS_ERROR("22","业务处理异常"),
    SYSTEM_ERROR("99","系统异常");

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private ResultEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
