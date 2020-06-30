package com.cloud.jarbase.exception;

import lombok.Data;

@Data
public class CustomerException extends RuntimeException {
    private String code;
    private Object[] args;  //可以仍例：e.getMessage() 或扔参数去拼msg

    public CustomerException(String code, Object... args) {
        super("error code:" + code);
        this.code = code;
        this.args = args;
    }

    public CustomerException(String code, Throwable e) {
        super(e);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

}
