package com.hjf.exception;

import com.hjf.enums.ResultEnum;

/**
 * 自定义异常类,
 * 知识点: 在构造器中接受的是自定义的枚举类;
 * 因为 Exception参数只能接受一个
 */
public class GirlException extends RuntimeException {

    private Integer code;

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
