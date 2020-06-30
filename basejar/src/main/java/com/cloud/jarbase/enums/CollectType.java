package com.cloud.jarbase.enums;

public enum CollectType {
    EWALLET(1, "Electronic wallet", "电子钱包"),
    BANKCARD(2, "Bank card", "银行卡"),
    CASH(3, "Cash", "现金");

    private int value;
    private String nameEn;
    private String nameCn;

    CollectType(int value, String nameEn, String nameCn) {
        this.value = value;
        this.nameEn = nameEn;
        this.nameCn = nameCn;
    }

    CollectType(int value) {
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
