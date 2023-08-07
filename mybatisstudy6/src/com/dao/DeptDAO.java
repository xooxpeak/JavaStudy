package com.dao;

import java.util.HashMap;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.DeptDTO;

/*
 * DeptDAO.java
 * session을 통해 쿼리문 실행 후
 * 메서드 호출한 쪽인 Service에 결과 반환
 * JDBC에서는 여기서 직접 DB 쿼리문 실행 코드 있었음
 * 
 */

public class DeptDAO {
	
	// 1. findAll
	// service에 있는 메서드
	// SqlSession = JDBC에서의 Connection 역할
	public List<DeptDTO> findAll(SqlSession session) {
		List<DeptDTO> list = session.selectList("DeptMapper.findAll");
		return list;
	}
	
	
	// 2. findByDeptno
	public DeptDTO findByDeptno(SqlSession session, int deptno) {
		DeptDTO dto = session.selectOne("DeptMapper.findByDeptno", deptno);
		return dto;
	}
	
	
	
	// 3. insert
	public int addDept(SqlSession session, DeptDTO dto) {
		int n = session.insert("DeptMapper2.addDept", dto);
		return n;
	}
	
	
	
	// 4. update
	public int updateDept(SqlSession session, HashMap<String, Object> map) {
		int n = session.update("DeptMapper.updateDept", map);
		return n;
	}
	
	
	
	// 5. delete
	public int deleteDept(SqlSession session, int deptno) {
		int n = session.delete("DeptMapper.deleteDept", deptno);
		return n;
	}
}



