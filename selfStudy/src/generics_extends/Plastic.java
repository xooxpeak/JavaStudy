package generics_extends;

public class Plastic extends Material {

	@Override
	public void doPrinting() {    // Material을 상속받았기 때문에 반드시 구현해야하는 메서드
		System.out.println("Plastic 재료로 출력합니다.");
	}

	public String toString() {
		return "재료는 Plastic입니다";
	}
}
