package cooperation;

public class Student {
	int grade;
	int money;
	String studentName;
	
	public Student() {	}

	public Student(int money, String studentName) {
		this.money = money;
		this.studentName = studentName;
	}
	
	// 버스 타기
	public void takeBus(Bus bus) {    // 매개변수로 버스 정보
		bus.take(1000);    // 버스에 돈 지불
		money -= 1000;     // 1000원 지불
	}
	
	// 지하철 타기
	public void takeSubway(Subway subway) { // 매개변수로 지하철 정보
		subway.take(1500);    // 지하철에 돈 지불
		money -= 1500;        // 1500원 지불
	}
		
	public void showinfo() {
		System.out.println(studentName + "님의 남은 잔액은" + money + "원 입니다.");
	}
	
	
}
