package holding.exercise.standard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import static util.PrintUtil.*;

public class Ex12Code {
	public void method1() {
		List<Integer> li1 = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4));
		List<Integer> li2 = new ArrayList<Integer>(Arrays.asList(5, 6, 7, 8, 9));
		ListIterator<Integer> it1 = li1.listIterator();
		ListIterator<Integer> it2 = li2.listIterator();
		println("li1: " + li1);
		println("li2: " + li2);
		while (it1.hasNext())
			it1.next();
		while (it2.hasNext()) {
			it2.next();
			it2.set(it1.previous());
		}
		println("li1: " + li1);
		println("li2: " + li2);
	}

	public void method2() {
		List<Integer> li1 = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4));
		List<Integer> li2 = new ArrayList<Integer>(Arrays.asList(5, 6, 7, 8, 9));
		ListIterator<Integer> it1 = li1.listIterator(5);
		ListIterator<Integer> it2 = li2.listIterator();
		println("li1: " + li1);
		println("li2: " + li2);
		while (it2.hasNext()) {
			it2.next();
			it2.set(it1.previous());
		}
		println("li1: " + li1);
		println("li2: " + li2);
	}

	public static void main(String[] args) {
		new Ex12Code().method1();
	}
}