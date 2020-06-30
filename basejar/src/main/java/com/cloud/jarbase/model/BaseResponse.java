package com.cloud.jarbase.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 基础返回Bean
 * @param <T>
 */
@Data
public class BaseResponse<T> implements Serializable {

    private String code;

    private String msg;

    private T data;

    public static final String SUCCESS = "00000";

    private static final String SUCCESS_MSG = "success";

    //默认成功
    public BaseResponse(){
        this.code = SUCCESS;
        this.msg = SUCCESS_MSG;
    };

//    public BaseResponse(String code) {
//        this.code = code;
//    }

    public BaseResponse(T data){
        this.code = SUCCESS;
        this.msg = SUCCESS_MSG;
        this.data = data;
    }

    public BaseResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public BaseResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
