package com.cloud.jarbase.enums;

public enum PaymentEnum {
    PAYMENT_LOCAL("00","LOCAL"),
    PAYMENT_SWIFT("01","SWIFT")
    ;
    private String code;

    private String desc;

    PaymentEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}