package com.example.multiDataSource.pojo.db;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Table(name = "school_info")
public class SchoolInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//学校名称
	private String name;

	//学校简介
	private String resume;

}
