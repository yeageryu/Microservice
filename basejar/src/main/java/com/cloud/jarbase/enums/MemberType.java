package com.cloud.jarbase.enums;

public enum MemberType {
    PERSONAL(0, "personal", "个人"),
    COMPANY(1, "enterprise", "企业");

    private int value;
    private String nameEn;
    private String nameCn;

    MemberType(int value, String nameEn, String nameCn) {
        this.value = value;
        this.nameEn = nameEn;
        this.nameCn = nameCn;
    }

    MemberType(int value) {
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