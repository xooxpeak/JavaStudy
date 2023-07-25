package generics_extends;

// extends 예약어로 사용할 수 있는 자료형에 제한을 둠
public class GenericPrinter <T extends Material> {
	private T material;

	public T getMaterial() {
		return material;
	}

	public void setMaterial(T material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return material.toString();
	}
	
	public void printing() {
		material.doPrinting();   // 상위 클래스 Material의 메서드 호출
	}
}
