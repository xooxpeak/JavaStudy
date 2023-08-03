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
	
	
	
	// ▣ select 기능하는 메서드
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

	
	
	// ▣ insert 기능하는 메서드 
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

	
	
	
	// ▣ update 기능하는 메서드 
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

	
	
	
	// ▣ DELETE 작업 
	@Override
	public int delete(int deptno) {
		// Connection때문에 try~catch 필요
		// close() 해야되는 것들은 finally에 쓰기 위해 다 바깥에 
		int n = 0;
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			// DAO 연동
			DeptDAO dao = new DeptDAO();
			n = dao.delete(con, deptno);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n;
	}


	// ▣ updateAndDelete 작업 (트랜잭션 처리)
	@Override
	public int updateAndDelete(DeptDTO dto, int deptno) {
		
		int n = 0;
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			// DAO 연동
			DeptDAO dao = new DeptDAO();
			
			// ----------------트랜잭션---------------------
			// 여러 개의 작업을 하나의 작업으로 처리할 때 씀
			// 둘 다 성공해서 실제 DB에 반영 => COMMIT
			// 또는
			// 둘 중에 하나라도 실패하면 모두 취소 => ROLLBACK
			
			// AutoCommit을 비활성화
			con.setAutoCommit(false);
			
			// 수정
			n = dao.update(con, dto);
			// 삭제
			n = dao.delete(con, deptno);
			// -------------------------------------------
			
			con.commit();   // 아무 문제가 없어서 커밋한다.
		}catch(SQLException e) {
			//catch에 잡혔다면 둘 중에 하나는 에러가 있다는 거니까 rollback
			try {
				if(con != null)con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}finally {
			try {
				if(con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n;
	}

	private void rollback() {
		// TODO Auto-generated method stub
		
	}

}
