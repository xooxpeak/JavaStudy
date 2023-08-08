package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.StudentDTO;

public interface MyBatisService {
	
	public List<StudentDTO> selectAllStudent();

}
