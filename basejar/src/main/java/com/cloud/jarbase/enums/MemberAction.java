package com.cloud.jarbase.enums;

public enum MemberAction {
    REGISTER(0,"register","注册"),
    PWDLOGIN(1, "login by password", "密码登录"),
    SMSLOGIN(2, "login by verification code", "验证码登录"),
    CHGLOGINPWD(3, "change login password", "改登录密码"),
    RESETLOGINPWD(4, "reset login password", "重置登录密码"),
    RECHARGE(8,"recharge","充值"),
    PAYMENT(19,"transfer","转账"),
    ;

    private int value;
    private String nameEn;
    private String nameCn;

    MemberAction(int value, String nameEn, String nameCn) {
        this.value = value;
        this.nameEn = nameEn;
        this.nameCn = nameCn;
    }

    MemberAction(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public String getNameCn() {
        return this.nameCn;
    }

}