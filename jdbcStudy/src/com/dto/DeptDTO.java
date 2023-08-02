package com.dto;


//dept 테이블의 하나의 행(레코드)을 저장하는 용도
public class DeptDTO {
	
	// DeptDTO 클래스의 변수명은 dept테이블의 컬럼명과 동일하게 지정
	int deptno;  // dept테이블의 deptno 컬럼 저장
	String dname; // dept테이블의 dname 컬럼 저장
	String loc;  // dept테이블의 loc 컬럼 저장
	
	// 생성자
	public DeptDTO() {}

	public DeptDTO(int deptno, String dname, String loc) {
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}

	// getter, setter
	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	// toString()
	@Override
	public String toString() {
		return deptno+"\t"+dname+"\t"+loc;
	}
	
	
}