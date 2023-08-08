package com.comfig;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlSessionFactory {

	static SqlSessionFactory sqlSessionFactory = null;
	static {
		
		String resource = "com/config/Configuration.xml";
		InputStream inputStream=null;   // 초기화
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory =
		  new SqlSessionFactoryBuilder().build(inputStream);
	}// end static 
	
	
	// ServiceImpl에 SqlSession 반환하는 메서드
	// 객체 생성하지않고 (new 없이) 메서드를 사용하기 위해 static으로 설정
	public static SqlSession getSession() {
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
}
