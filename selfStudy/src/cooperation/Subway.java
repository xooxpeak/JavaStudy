package cooperation;

public class Subway {
	int lineNumber; 
	int passengerCount;
	int money;
	
	public Subway(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Subway(int lineNumber, int passengerCount, int money) {
		this.lineNumber = lineNumber;
		this.passengerCount = passengerCount;
		this.money = money;
	}	
	
	// 승객 태우기
	public void take(int money) {
		passengerCount++;
		this.money += money;       // 받은 돈만큼 증가
	}
	
	public void showInfo() {
		System.out.println("지하철 " + lineNumber + "호선의 승객은" + passengerCount + "명이고, 지하철 수입은" + money + "원 입니다.");
	}

}
