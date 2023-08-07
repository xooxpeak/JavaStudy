package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.DeptDTO;

/*
 * DeptService.java
 * 추상메서드 있는 인터페이스 
 * 
 */

public interface DeptService {
	public List<DeptDTO> findAll();  // 1.findAll
	public DeptDTO findByDeptno(int deptno); // 2.findByDeptno
	public int addDept(DeptDTO dto); // 3.insert
	public int updateDept(HashMap<String, Object> map);  // 4.update
	public int deleteDept(int deptno);  // 5.delete
}
