package com.cloud.jarbase.enums;

public enum Language {
    CHINESE("cn", "Chinese", "中文"),
    ENGLISH("en", "English", "英文");

    private String value;
    private String nameEn;
    private String nameCn;

    Language(String value, String nameEn, String nameCn) {
        this.value = value;
        this.nameEn = nameEn;
        this.nameCn = nameCn;
    }

    Language(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public String getNameCn() {
        return this.nameCn;
    }

}