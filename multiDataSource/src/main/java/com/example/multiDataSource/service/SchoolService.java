package com.example.multiDataSource.service;

import com.example.multiDataSource.dao.ISchoolMapper;
import com.example.multiDataSource.pojo.db.SchoolInfo;
import lombok.AllArgsConstructor;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SchoolService  {

	private ISchoolMapper mapper;

	public int insert(SchoolInfo domain) {
		return mapper.insert(domain);
	}
}
