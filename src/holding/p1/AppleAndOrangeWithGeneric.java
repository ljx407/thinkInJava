package holding.p1;

import java.util.ArrayList;
import java.util.List;

public class AppleAndOrangeWithGeneric {

	public static void main(String[] args) {
		List<Apple> apples = new ArrayList<Apple>();
		apples.add(new Apple(1));
		// apples.add(new Orange());
		if (apples != null && !apples.isEmpty()) {
			for (Apple apple : apples) {
				System.out.println(apple.getId());
			}
		}
	}

}
