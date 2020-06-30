package com.cloud.jarbase.model;

import com.cloud.jarbase.config.CreatedTimeFuncation;
import com.cloud.jarbase.config.UpdatedTimeFuncation;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class BaseTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreatedTimeFuncation
    private Long createTime;

    @UpdatedTimeFuncation
    private Long updateTime;
}

