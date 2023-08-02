package exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertTest {

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

		try{
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "insert into dept ( deptno, dname, loc) "
					+ " values( ?, ?, ?)";    // ?는 바인딩 변수로서 나중에 값을 설정한다.
		pstmt = con.prepareStatement(sql);   // ★	
			// ? 대신에 값 설정하기
			/*
			 * pstmt.setXXX(?의위치, 값)
			 * 
			 */
			pstmt.setInt(1, 12);    // 첫번째 물음표는 deptno라서 정수 : Int
			pstmt.setString(2, "개발");
			pstmt.setString(3, "서울");
			
			int num = pstmt.executeUpdate(); 
			System.out.println("레코드 생성 갯수:" + num);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//역순
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}//end main

}
