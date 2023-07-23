package mobile;

public class Ltab extends Mobile {

	public Ltab() {	}

	public Ltab(String mobileName, int batterySize, String osType) {
		super(mobileName, batterySize, osType);
	}

	@Override
	public int operate(int time) {
		int size = getBatterySize()-(time*10);
		setBatterySize(size);
		return size;
	}

	@Override
	public int charge(int time) {
		int size = getBatterySize()+(time*10);
		setBatterySize(size);
		return size;
	}
	
	
	
	

}
