package cooperation;

public class takeTrans {

	public static void main(String[] args) {

		Student james = new Student(5000, "James");
		Student bit = new Student(10000, "Bit");
		
		// 버스 타는 제임스
		Bus bus100 = new Bus(100);   // 100번 버스
		james.takeBus(bus100);
		james.showinfo();
		bus100.showInfo();
		
		// 지하철 타는 bit
		Subway subwaygreen = new Subway(2);   // 2 호선
		bit.takeSubway(subwaygreen);
		bit.showinfo();
		subwaygreen.showInfo();
		
	}

}
