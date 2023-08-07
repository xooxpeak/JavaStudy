package com.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.comfig.MySqlSessionFactory;
import com.dao.DeptDAO;
import com.dto.DeptDTO;

/*
 * DeptServiceImpl.java
 * DeptService 추상메서드가 실제로 구현된 클래스
 * main 메서드에서 호출된 작업 처리
 * => main 메서드 요청에 따라 DAO를 호출하고 결과 값을 main에 반환
 * => main-DAO 사이에서 작업
 * 
 */

public class DeptServiceImpl implements DeptService {
	
	@Override    // 1. findAll
	public List<DeptDTO> findAll() {
		List<DeptDTO> list = null;
		// SqlSession 얻기
		SqlSession session = MySqlSessionFactory.getSession();
		try {
		///////////////////////////////
		// DAO 연동
		DeptDAO dao = new DeptDAO();
		list = dao.findAll(session);
		//////////////////////////////
		}finally {
		// close
		session.close();
		}
		
		return list;
	}

	
	
	
	@Override    // 2. findByDeptno
	public DeptDTO findByDeptno(int deptno) {
		DeptDTO dto = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			//DAO 연동
			DeptDAO dao = new DeptDAO();
			dto = dao.findByDeptno(session, deptno);
		}finally {
			session.close();
		}
		
		
		return dto;
	}




	@Override   // 3. insert
	public int addDept(DeptDTO dto) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
			try {
			// DAO 연동
				DeptDAO dao = new DeptDAO();
				n = dao.addDept(session, dto);
				session.commit();    // 명시적 commit
			}finally {
				session.close();
			}
		return n;
	}




	@Override   // 4.update
	public int updateDept(HashMap<String, Object> map) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			//DAO 연동
			DeptDAO dao = new DeptDAO();
			n = dao.updateDept(session, map);
			session.commit();
		}finally {
			session.close();
		}
		
		
		return n;
	}




	@Override
	public int deleteDept(int deptno) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			//DAO 연동
			DeptDAO dao = new DeptDAO();
			n = dao.deleteDept(session, deptno);
			session.commit();
		}finally {
			session.close();
		}
		
		
		return n;
	}
	
}






// SqlSession session = sqlSessionFactory.openSession();
//		try {
//			
//		}finally {
//			session.close();
//		}
//		
//		
//		return null;
//		
