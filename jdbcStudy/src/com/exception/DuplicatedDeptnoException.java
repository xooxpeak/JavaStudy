package com.exception;

// Exception을 상속받는 사용자 정의 예외클래스
// => 예외 발생 시 try~catch 또는 throws에 사용할 수 있다.
public class DuplicatedDeptnoException extends Exception{

	// 필요시 변수, 메서드 추가 가능
	
	public DuplicatedDeptnoException(String message) {
		super(message);
	}
}
