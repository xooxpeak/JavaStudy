package generics_03;

public class Point<T, V> {
	T x;
	V y;
	
	Point(T x, V y){
		this.x = x;
		this.y = y;
	}
	
	// 제네릭 메서드
	public T getX() {
		return x;
	}
	
	public V getY() {
		return y;
	}
	
	

}
