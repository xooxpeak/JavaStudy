package mobile;

public abstract class Mobile {

	private String mobileName;
	private int batterySize;
	private String osType;
	
	public Mobile() {}

	public Mobile(String mobileName, int batterySize, String osType) {
		this.mobileName = mobileName;
		this.batterySize = batterySize;
		this.osType = osType;
	}
	
	
	// 추상메서드: 추상 클래스에서 추상메서드는 abstract 필수 !
	public abstract int operate(int time);
	public abstract int charge(int time);
	

	public String getMobileName() {
		return mobileName;
	}

	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}

	public int getBatterySize() {
		return batterySize;
	}

	public void setBatterySize(int batterySize) {
		this.batterySize = batterySize;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}
	
	
	
	
}
