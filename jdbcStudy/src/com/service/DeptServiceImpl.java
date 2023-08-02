package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dao.DeptDAO;
import com.dto.DeptDTO;
import com.exception.DuplicatedDeptnoException;

// 오라클 데이터베이스와 연결하기 위해 Connection을 얻는 작업을 하기 위한 클래스
// Connection 얻는 이유? 트랜잭션을 처리하려고
// 얻은 Connection을 실제 데이터베이스와 연동하는 DeptDAO 클래스에 인자로 전달하여 사용된다.
public class DeptServiceImpl implements DeptService {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "SCOTT";
	String passwd = "TIGER";

	public DeptServiceImpl() {
		try {
			Class.forName(driver);    // 드라이버 로딩
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// < select 기능하는 메서드 >
	// Connection 까지만 얻는다. 나머지 작업은 DAO에서 처리
	@Override
	public List<DeptDTO> findAll(){
		
		List<DeptDTO> list = null;   // ★
		Connection con = null;

		// Connection 맺기
	    try{
	       con = DriverManager.getConnection(url, userid, passwd);
	       // DAO 접근
	       DeptDAO dao = new DeptDAO();    // ★
	       list = dao.findAll(con);   // ★
	    }catch(SQLException e){
	    	e.printStackTrace();
	    }finally {                  // close()
			try {
				if(con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;   // ★ 리턴
	}

	
	// < insert 기능하는 메서드 >
	// Connection 까지만 얻는다. 나머지 작업은 DAO에서 처리
	@Override
	public int insert(DeptDTO dto) throws DuplicatedDeptnoException {
		int n = 0;
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
	        // DAO 연동
			DeptDAO dao = new DeptDAO();
			n = dao.insert(con, dto);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n;
	}

	
	// < update 기능하는 메서드 >
	// Connection 까지만 얻는다. 나머지 작업은 DAO에서 처리
	@Override
	public int update(DeptDTO dto) {
		int n = 0;
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			// DAO 연동
			DeptDAO dao = new DeptDAO();
			n = dao.update(con, dto);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n;
	}

}
