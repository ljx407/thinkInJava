package holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import util.ArrayUtil;
import static util.PrintUtil.*;

public class Holding11_05 {
	public static int printNum = 1;
	List<Pet> pet = null;
	Dog dog2 = new Dog("dog2");

	@Before
	public void before() {
		pet = Pet.arrayList(10);
		print(pet);
	}

	@Test
	public void testGet() {
		Pet pet1 = pet.get(1);
		print(pet1);
	}

	@Test
	public void testAdd() {
		pet.add(dog2);
		print(pet);
	}

	@Test
	public void testContains() {
		boolean flag = pet.contains(dog2);
		print(flag);
		pet.add(dog2);
		flag = pet.contains(dog2);
		print(flag);
	}

	@Test
	public void testIndexOf() {
		int index = pet.indexOf(dog2);
		print(index);
		pet.add(dog2);
		index = pet.indexOf(dog2);
		print(index);
	}

	@Test
	public void testRemove() {
		boolean flag = pet.remove(dog2);
		print(flag);
		pet.add(dog2);
		print(pet);
		flag = pet.remove(dog2);
		print(flag);
		print(pet);
		pet.add(dog2);
		print(pet);
		int index = pet.indexOf(dog2);
		print(index);
		Pet dogRemove = pet.remove(index);
		print(dogRemove);
		print(pet);
	}

	@Test
	public void testSubList() {
		List<Pet> subList = pet.subList(0, 4);
		print(subList);
	}

	@Test
	public void testContainsAll() {
		List<Pet> subList = pet.subList(0, 4);
		print(subList);
		boolean flag = pet.containsAll(subList);
		print(flag);
		Collections.shuffle(subList);
		print(subList);
		flag = pet.containsAll(subList);
		print(flag);
	}

	@Test
	public void removeAll() {
		List<Pet> subList = pet.subList(0, 4);
		print(subList);
		pet.removeAll(subList);
		print(pet);
	}

	@Test
	public void testRetainAll() {
		List<Pet> subList = pet.subList(0, 4);
		print(subList);
		pet.retainAll(subList);
		print(pet);
	}

	@Test
	public void addAllTest() {
		List<Pet> anotherList = Pet.arrayList(3);
		print(anotherList);
		pet.addAll(anotherList);
		print(pet);
		pet.removeAll(anotherList);
		print(pet);
		pet.addAll(1, anotherList);
		print(pet);
	}

	@Test
	public void toArrayTest() {
		Object[] objs = pet.toArray();
		print(objs[3]);
		print(Arrays.toString(objs));
		print(ArrayUtil.join(objs, "-"));
		Pet[] petArray = pet.toArray(new Pet[0]);
		print(petArray[3].getId());
	}

	@Test
	public void setTest() {
		pet.set(0, dog2);
		print(pet);
	}

	@Test
	public void isEmptyTest() {
		print(pet.isEmpty());
		pet.clear();
		print(pet.isEmpty());
	}

	public void print(Object o) {
		println(printNum + ": " + o);
		printNum++;
	}
}

class Pet {
	int id;
	String name;

	public Pet() {
		super();
	}

	public Pet(int id) {
		this(id, null);
	}

	public Pet(String name) {
		this(0, name);
	}

	public Pet(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static List<Pet> arrayList(int count) {
		List<Pet> arrayList = new ArrayList<Pet>();
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			int key = random.nextInt(5);
			arrayList.add(randomNext(key));
		}
		return arrayList;
	}

	private static Pet randomNext(int key) {
		Pet pet = null;
		switch (key) {
		case 0:
			pet = new Cat("cat");
			break;
		case 1:
			pet = new Dog("dog");
			break;
		case 2:
			pet = new Tiger("tiger");
			break;
		case 3:
			pet = new Lion("lion");
			break;
		default:
			pet = new Snake("snake");
			break;
		}
		return pet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return name;
	}
}

class Cat extends Pet {
	public Cat(String name) {
		super(name);
	}
}

class Dog extends Pet {
	public Dog(String name) {
		super(name);
	}
}

class Tiger extends Pet {
	public Tiger(String name) {
		super(name);
	}
}

class Lion extends Pet {
	public Lion(String name) {
		super(name);
	}
}

class Snake extends Pet {
	public Snake(String name) {
		super(name);
	}
}