import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DeptMain {

	public static void main(String[] args) throws Exception {
		
		// Configuration.xml 파일 읽기
		String resource = "Configuration.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory =
		  new SqlSessionFactoryBuilder().build(inputStream);
		
		// SqlSession 얻기 (복붙)
		SqlSession session = sqlSessionFactory.openSession();

		// findByDeptno 호출
		DeptDTO dto = session.selectOne("DeptMapper.findByDeptno", 10);
		System.out.println(dto);
		
		// findAll 호출
		List<DeptDTO> list = session.selectList("DeptMapper.findAll");
		for (DeptDTO xxx : list) {
			System.out.println(xxx);
		}
		System.out.println("##########################################");
		
		
		// findByDeptnoAndDname
		DeptDTO dto2 = new DeptDTO();    // 파라미터를 dto2에 넣어주는 작업
		dto2.setDeptno(10);
		dto2.setDname("인사");
		List<DeptDTO> list2 = session.selectList("DeptMapper.findByDeptnoAndDname", dto2);
		for (DeptDTO xxx : list2) {
			System.out.println(xxx);
		}
		System.out.println("##########################################");

		
		// findByDeptnoAndDnameMap
		HashMap<String, Object> map =     // 두 개를 다 받아줘야하니까 object로
				new HashMap<>();     
		map.put("xxx", 10);
		map.put("yyy", "인사");
		
		List<DeptDTO> list3 = session.selectList("DeptMapper.findByDeptnoAndDnameMap", map);
		for (DeptDTO xxx : list3) {
			System.out.println(xxx);
		}
		System.out.println("##########################################");
		
		
		// findAllPage
		RowBounds bounds = new RowBounds(1, 3);
		List<DeptDTO> list4 = session.selectList("DeptMapper.findAllPage", null, bounds);
		for (DeptDTO xxx : list4) {
			System.out.println(xxx);
		}
		
		session.close();
	}

}
