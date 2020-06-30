package com.cloud.jarbase.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "multi_language")
public class Multilingual extends BaseTable {

    private String msgCode;
    private String msgCn;
    private String msgEn;
    private String remark;
}
