import java.util.List;
import java.util.Scanner;

import com.dto.DeptDTO;
import com.exception.DuplicatedDeptnoException;
import com.service.DeptService;
import com.service.DeptServiceImpl;

public class DeptMain {

	public static void main(String[] args) {

		// 화면처리 추가
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("1. 전체목록");
			System.out.println("2. 저장하기");
			System.out.println("3. 수정하기");
			System.out.println("4. 삭제하기");
			System.out.println("5. 삭제 및 수정하기");  // 여러개의 작업이 하나처럼 : 트랜잭션
			System.out.println("0. 종료");
			System.out.println("-----------------------");
			
			// 문자열로 읽기
			String num = scan.nextLine();     // 한 줄 읽기
			if("1".equals(num)) {      // 만약 1이면. 문자열 비교는 equals
				// 서비스 연동
				DeptService service = new DeptServiceImpl();    // 다형성으로 객체 생성
				List<DeptDTO> list = service.findAll();
				System.out.println("부서번호\t 부서명\t 부서위치");
				System.out.println("-----------------------");
				for (DeptDTO dto : list) {
					System.out.println(dto);      // dto.toString() 포맷을 잘 만들어야한다. 
				}
			}else if("2".equals(num)) {
				System.out.println("부서번호를 입력하시오");
				String deptno = scan.next();
				System.out.println("부서명을 입력하시오");
				String dname = scan.next();
				System.out.println("부서위치를 입력하시오");
				String loc = scan.next();
				// 입력받은 3가지 값을 DTO에 저장해서 서비스를 거쳐 DAO까지 전달한다.
				DeptDTO dto = new DeptDTO(Integer.parseInt(deptno), dname, loc);
				// 서비스 연동
				DeptService service = new DeptServiceImpl();
				int n=0;
				try {
					n = service.insert(dto);
				} catch (DuplicatedDeptnoException e) {
					System.out.println(e.getMessage());
				}
				System.out.println(n+" 개가 저장되었습니다");
				
			}else if("3".equals(num))	{
				System.out.println("수정할 부서번호를 입력하시오");
				String deptno = scan.next();
				System.out.println("수정할 부서명을 입력하시오");
				String dname = scan.next();
				System.out.println("수정할 부서위치를 입력하시오");
				String loc = scan.next();
				DeptDTO dto = new DeptDTO(Integer.parseInt(deptno), dname, loc);
				
				// 서비스 연동
				DeptService service = new DeptServiceImpl();
				int n= service.update(dto);
				System.out.println(n+" 개가 수정되었습니다");
				
			}else if("4".equals(num)) {
				System.out.println("삭제할 부서번호를 입력하시오");
				String deptno = scan.next();
				// Service 연동
				DeptService service = new DeptServiceImpl();
				int n = service.delete(Integer.parseInt(deptno));
				System.out.println(n+" 개가 삭제되었습니다");
				
			}else if("5".equals(num)) {
				// 삭제 및 수정
				// 수정할 데이터
				System.out.println("수정할 부서번호를 입력하시오");
				String deptno = scan.next();
				System.out.println("수정할 부서명을 입력하시오");
				String dname = scan.next();
				System.out.println("수정할 부서위치를 입력하시오");
				String loc = scan.next();
				DeptDTO dto =        // 위에 세가지를 dto에 담기
						new DeptDTO(Integer.parseInt(deptno), dname, loc);
				
				// 삭제할 데이터
				System.out.println("삭제할 부서번호를 입력하시오");
				String deptno2 = scan.next();
				
				// Service 연동
				DeptService service = new DeptServiceImpl();
				int n = service.updateAndDelete(dto, Integer.parseInt(deptno2));
				
				
			}else if("0".equals(num)) {
				System.out.println("프로그램 종료");
				System.exit(0);    // exit : 프로그램 정상종료 
			}
			
		}//end while
		
	}//end main

}//end class
