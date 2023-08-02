package exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {

	public static void main(String[] args) {

	// 1. 오라클 데이터베이스 연동을 위한 4가지 정보를 문자열에 저장	
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String userid="SCOTT";
		String passwd="TIGER";
	
		
	//2. 드라이버 로딩	
		try {
			Class.forName(driver);
		} catch(ClassNotFoundException e) {
			//System.out.println(e.getMessage());
		     e.printStackTrace();
		}
		
		
	//3. Connection	( 오라클과 자바 연결 )
		Connection con = null;               // finally 쓰기 위해 블록 밖에서 작성
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "select deptno as no, dname, loc from dept";
			pstmt
			= con.prepareStatement(sql);
			rs = pstmt.executeQuery(); 
			while(rs.next()) {
				int deptno = rs.getInt("no");   // getInt(1) 가능
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				System.out.println(deptno+"\t"+dname+"\t"+loc);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//역순
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}//end main

}
