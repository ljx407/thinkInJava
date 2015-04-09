package holding.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static util.PrintUtil.*;

public class Ex05 {
	public static int printNum = 1;
	List<Integer> list;
	Integer integerNum = 2;

	@Before
	public void before() {
		list = GeneratorIneger.arrayList(10);
		print(list);
	}

	@Test
	public void testGet() {
		Integer pet1 = list.get(1);
		print(pet1);
	}

	@Test
	public void testAdd() {
		list.add(integerNum);
		print(list);
	}

	@Test
	public void testContains() {
		boolean flag = list.contains(integerNum);
		print(flag);
		flag = list.add(integerNum);
		print(list);
		print(flag);
	}

	@Test
	public void testIndexOf() {
		int index = list.indexOf(integerNum);
		print(index);
		list.add(integerNum);
		print(list);
		index = list.indexOf(integerNum);
		print(index);
	}

	@Test
	public void testRemove() {
		boolean flag = list.remove(integerNum);
		print(flag);
		list.add(integerNum);
		print(list);
		flag = list.remove(integerNum);
		print(flag);
		print(list);
		list.add(integerNum);
		print(list);
		int index = list.indexOf(integerNum);
		print(index);
		Integer dogRemove = list.remove(index);
		print(dogRemove);
		print(list);
	}

	@Test
	public void testSubList() {
		List<Integer> subList = list.subList(0, 4);
		print(subList);
	}

	@Test
	public void testContainsAll() {
		List<Integer> subList = list.subList(0, 4);
		print(subList);
		boolean flag = list.containsAll(subList);
		print(flag);
		Collections.shuffle(subList);
		print(subList);
		flag = list.containsAll(subList);
		print(flag);
	}

	@Test
	public void removeAll() {
		List<Integer> subList = list.subList(0, 4);
		print(subList);
		list.removeAll(subList);
		print(list);
	}

	@Test
	public void testRetainAll() {
		List<Integer> subList = list.subList(0, 4);
		print(subList);
		list.retainAll(subList);
		print(list);
	}

	@Test
	public void addAllTest() {
		List<Integer> anotherList = GeneratorIneger.arrayList(3);
		print(anotherList);
		list.addAll(anotherList);
		print(list);
		list.removeAll(anotherList);
		print(list);
		list.addAll(1, anotherList);
		print(list);
	}

	@Test
	public void toArrayTest() {
		Object[] objs = list.toArray();
		print(objs[3]);
		Integer[] petArray = list.toArray(new Integer[0]);
		print(petArray);
	}

	@Test
	public void setTest() {
		list.set(0, integerNum);
		print(list);
	}

	@Test
	public void isEmptyTest() {
		print(list.isEmpty());
		list.clear();
		print(list.isEmpty());
	}

	public void print(Object o) {
		println(printNum + ": " + o);
		printNum++;
	}
}

class GeneratorIneger {
	public static List<Integer> arrayList(int count) {
		List<Integer> arrayList = new ArrayList<Integer>();
		for (int i = 0; i < count; i++) {
			arrayList.add(randomNext());
		}
		return arrayList;
	}

	private static Integer randomNext() {
		Random random = new Random();
		return random.nextInt(100);
	}
}