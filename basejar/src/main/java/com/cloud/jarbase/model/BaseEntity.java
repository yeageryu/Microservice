package com.cloud.jarbase.model;

import lombok.Data;

@Data
public class BaseEntity {

    private Integer id;

    private Integer page = 1;

    private Integer rows = 10;

    //查询用开始时间
    private String queryStartTime;

    //查询用结束时间
    private String queryEndTime;
}

