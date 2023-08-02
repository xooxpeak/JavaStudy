package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.DeptDTO;
import com.exception.DuplicatedDeptnoException;

// 실제 데이터베이스 (Oracle의 dept테이블)와 연동하는 클래스
// dept 테이블의 하나의 레코드는 DeptDTO에 저장
// 누적하기 위하여 ArrayList<DeptDTO>를 사용
// 주의할 점 : Connection을 DAO 클래스에서 close()하면 안되고 반드시 Service 클래스에서 close() 시켜야한다.
public class DeptDAO {

	// < select 기능하는 메서드 >
	public List<DeptDTO> findAll(Connection con){
		
		// 다형성 통해 DeptDTO 누적용
		List<DeptDTO> list = new ArrayList<DeptDTO>();
		PreparedStatement pstmt=null;
	    ResultSet rs = null;
	    try{
	       String sql = "select deptno as no, dname, loc from dept";
	       pstmt = con.prepareStatement(sql); //에러발생시
	       rs = pstmt.executeQuery();
	       // 결과값인 ResultSet에서 데이터를 얻기
	       while(rs.next()) {
	    	  int deptno = rs.getInt("no"); // getInt(1) 가능
	    	  String dname = rs.getString("dname");  // getString(2)
	    	  String loc = rs.getString("loc");  // // getString(3)
	    	  // 생성자 통해 DTO에 저장. 한 번 돌때마다 DTO 생성.
	    	  DeptDTO dto = new DeptDTO(deptno, dname, loc);
	    	  // 누적
	    	  list.add(dto);
	       }
	    }catch(SQLException e){
	    	e.printStackTrace();
	    }finally {
			try {
				//역순
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		 return list;
	}//

	
	
	
// < insert 작업 >
	public int insert(Connection con, DeptDTO dto ) throws DuplicatedDeptnoException {
		int num = 0;
		PreparedStatement pstmt=null;
	    try{
	    	String sql = "insert into dept ( deptno, dname, loc) "
       		        + " values( ?, ?, ? )";
	       pstmt = con.prepareStatement(sql); //에러발생시
	       pstmt.setInt(1, dto.getDeptno());  // deptno 값은 중복되지 않도록 확인할 것.
           pstmt.setString(2, dto.getDname());
           pstmt.setString(3, dto.getLoc());
           num = pstmt.executeUpdate();
	    }catch(SQLException e){
//	    	e.printStackTrace();
	    	throw new DuplicatedDeptnoException(dto.getDeptno()+" 값이 중복됨");
	    }finally {
			try {
				if(pstmt != null)pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return num;
}

public int update(Connection con, DeptDTO dto) {
	int num = 0;
	PreparedStatement pstmt=null;
    try{
    	String sql = "update dept set dname=?, loc=? where deptno=?"; 
    	pstmt = con.prepareStatement(sql); //에러발생시
    	pstmt.setInt(3, dto.getDeptno());    // deptno 값은 중복되지 않도록 확인할것.
		pstmt.setString(1, dto.getDname());
		pstmt.setString(2, dto.getLoc());
		num = pstmt.executeUpdate(); 
       
    }catch(SQLException e){
    	e.printStackTrace();
    }finally {
		try {
			if(pstmt != null)pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return num;
}

	
}	




