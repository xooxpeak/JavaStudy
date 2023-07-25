package generics;

// 파우더와 플라스틱 액체를 재료로 모형을 출력하는 프린터를 제네릭 클래스로 정의
public class GenericPrinter<T> {    // 제네릭 클래스 사용
	private T material;   // T 자료형으로 변수 선언

	public T getMaterial() {   // T 자료형 변수 material을 반환하는 제네릭 메서드
		return material;
	}

	public void setMaterial(T material) {
		this.material = material;
	}

	public String toString() {
		return material.toString();
	}

}
