import java.util.HashMap;
import java.util.List;

import com.dto.DeptDTO;
import com.service.DeptService;
import com.service.DeptServiceImpl;

/*
 *  main 메서드를 가지는 클래스
 *  실제 애플리케이션이 먼저 실행되는 곳
 *  service 객체를 이용하여 SQL 처리 및 결과 출력
 *  
 */

public class DeptMain {

	public static void main(String[] args) {
		
		// 서비스 연동
		// 1. 전체 목록 findAll
		DeptService service = new DeptServiceImpl();
		List<DeptDTO> list = service.findAll();
		for (DeptDTO dto : list) {
			System.out.println(dto);
		}
		System.out.println("######################");
		
		
		
		// 2. findByDeptno
		DeptDTO xxx = service.findByDeptno(20);
		System.out.println(xxx);
		System.out.println("########################");
		
		
		
		// 3. 저장 insert
//		int n = service.addDept(new DeptDTO(99, "관리", "부산"));
//		System.out.println(n+" 개가 저장됨.");
		
		
		
		// 4. 수정 update
		HashMap<String, Object> map = new HashMap<>();
		map.put("deptno", 99);
		map.put("dname", "관리과");
		map.put("loc", "부산시");
				
		int n2 = service.updateDept(map);
		System.out.println(n2+" 개가 수정됨.");
		
		
		
		//5. 삭제 delete
		int n3 = service.deleteDept(99);
		System.out.println(n2+" 개가 삭제됨.");
		
	}

}
