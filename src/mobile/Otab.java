package mobile;

public class Otab extends Mobile {

	public Otab() {	}

	public Otab(String mobileName, int batterySize, String osType) {
		super(mobileName, batterySize, osType);
	}

	
	@Override
	public int operate(int time) {
		int size = getBatterySize()-(time*12);
		setBatterySize(size);
		return size;
	}

	@Override
	public int charge(int time) {
		int size = getBatterySize()+(time*8);
		setBatterySize(size);
		return size;
	}

}
