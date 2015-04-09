package holding.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static util.PrintUtil.*;

public class Ex06 {
	public static int printNum = 1;
	List<String> list;
	String str = "2abc";

	@Before
	public void before() {
		list = GeneratorString.arrayList(10);
		print(list);
	}

	@Test
	public void testGet() {
		String pet1 = list.get(1);
		print(pet1);
	}

	@Test
	public void testAdd() {
		list.add(str);
		print(list);
	}

	@Test
	public void testContains() {
		boolean flag = list.contains(str);
		print(flag);
		list.add(str);
		print(list);
		flag = list.contains(str);
		print(flag);
		String another = new String("2abc");
		String another1 = new String("2abc");
		print(another.equals(another1));
		print(another == another1);
		flag = list.contains(another);
		print(flag);
	}

	@Test
	public void testIndexOf() {
		int index = list.indexOf(str);
		print(index);
		list.add(str);
		print(list);
		index = list.indexOf(str);
		print(index);
	}

	@Test
	public void testRemove() {
		boolean flag = list.remove(str);
		print(flag);
		list.add(str);
		print(list);
		flag = list.remove(str);
		print(list);
		print(flag);
		list.add(str);
		print(list);
		int index = list.indexOf(str);
		print(index);
		String dogRemove = list.remove(index);
		print(list);
		print(dogRemove);
	}

	@Test
	public void testSubList() {
		List<String> subList = list.subList(0, 4);
		print(subList);
	}

	@Test
	public void testContainsAll() {
		List<String> subList = list.subList(0, 4);
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
		List<String> subList = list.subList(0, 4);
		print(subList);
		list.removeAll(subList);
		print(list);
	}

	@Test
	public void testRetainAll() {
		List<String> subList = list.subList(0, 4);
		print(subList);
		list.retainAll(subList);
		print(list);
	}

	@Test
	public void addAllTest() {
		List<String> anotherList = GeneratorString.arrayList(3);
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
		String[] petArray = list.toArray(new String[0]);
		print(petArray);
	}

	@Test
	public void setTest() {
		list.set(0, str);
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

class GeneratorString {
	public static List<String> arrayList(int count) {
		List<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			arrayList.add(randomNext());
		}
		return arrayList;
	}

	private static String randomNext() {
		Random random = new Random();
		return random.nextInt(100) + "abc";
	}
}