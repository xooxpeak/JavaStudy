package cooperation;

public class Bus {
	int busNumber;
	int passengerCount;
	int money;
	
	public Bus(int busNumber) {
		this.busNumber = busNumber;
	}

	// 승객 태우는 메서드
	public void take(int money) {
		passengerCount++;
		this.money += money;     // 받은 돈 만큼 돈 증가
	}
	
	public void showInfo() {
		System.out.println("버스" + busNumber + "번의 승객은 " + passengerCount + "명이고, 버스 수입은" + money + "원 입니다.");
	}
	
	

}
