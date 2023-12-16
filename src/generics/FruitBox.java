package generics;

import java.io.Serializable;
import java.util.ArrayList;

public class FruitBox<T extends Fruit & Serializable> extends ArrayList<T> {
	
	private String type = "undefine";
	
	public FruitBox() {
		super();
	}
	
	public String getType() {
		return type;
	}
	
	public boolean add(T t) {
		boolean add = super.add(t);
		type = super.get(0).getClass().getSimpleName();
		return add;
	}
	
	public float getWeight() {
		float boxWeight = 0f;
		for(T t : this) {
			boxWeight += t.weight;
		}
		return boxWeight;
	}
	
	public void moveTo(FruitBox box) {
		if(box.getType().equals(this.getType())) {
			box.addAll(this);
			this.removeAll(this);
		}	else {
			UnsupportedOperationException e =
							new UnsupportedOperationException(this.getType() + " incompatible " + box.getType());
			System.out.println(e);
		}
	}
	
	public int compareTo(FruitBox box) {
		if(this.getWeight() == box.getWeight())
			return 0;
		return 1;
	}
	
	public static void main(String[] args) {
		FruitBox<Apple> apples = new FruitBox<Apple>();
		for(int i = 0; i < 3; i++) {
			apples.add(new Apple());
		}

		System.out.println("apples: " + apples);
		System.out.println(apples.getWeight());
		
		FruitBox<Apple> apples2 = new FruitBox<Apple>();
		for(int i = 0; i < 2; i++) {
			apples2.add(new Apple());
		}
		System.out.println("apples2: " + apples2);
		System.out.println(apples2.getWeight());
		
		FruitBox<Orange> orangs = new FruitBox<Orange>();
		for(int i = 0; i < 4; i++) {
			orangs.add(new Orange());
		}
		System.out.println("orangs: " + orangs);
		System.out.println(orangs.getWeight());
		
		FruitBox<Orange> orangs2 = new FruitBox<Orange>();
		for(int i = 0; i < 2; i++) {
			orangs2.add(new Orange());
		}
		System.out.println("orangs2: " + orangs2);
		System.out.println(orangs2.getWeight());
		
		System.out.println(orangs2.compareTo(apples));
		System.out.println(apples.compareTo(apples2));
		
		apples.moveTo(apples2);
		
		System.out.println("apples: " + apples);
		System.out.println("apples2: " + apples2);
		
		apples2.moveTo(orangs);
	}
}

class Fruit {
	float weight;
}

class Apple extends Fruit implements Serializable {
	
	public Apple() {
		weight = 1.0f;
	}
	
	@Override
	public String toString() {
		return "Apple=" + weight;
	}
}

class Orange extends Fruit implements Serializable{
	
	public Orange() {
		weight = 1.5f;
	}
	
	@Override
	public String toString() {
		return "Orange=" + weight;
	}
}