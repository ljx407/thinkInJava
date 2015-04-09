package holding;

import static util.PrintUtil.print;
import static util.PrintUtil.println;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

public class Holding11_06 {
	@Test
	public void testIterator() {
		List<Pet> pets = Pet.arrayList(10);
		println(pets);
		Iterator<Pet> iterator = pets.iterator();
		while (iterator.hasNext()) {
			Pet pet = iterator.next();
			print(pet + " ");
		}
		println();
		for (Pet pet : pets) {
			print(pet + " ");
		}
		println();
		iterator = pets.iterator(); // refresh
		for (int i = 0; i < 3; i++) {
			if (iterator.hasNext()) {
				iterator.next();
				iterator.remove();
			}
		}
		println(pets);
	}

	@Test
	public void testDisplay() {
		List<Pet> list = Pet.arrayList(10);
		println(list);
		ArrayList<Pet> arrayList = new ArrayList<Pet>(list);
		display(arrayList.iterator());
		LinkedList<Pet> linkedList = new LinkedList<Pet>(list);
		display(linkedList.iterator());
		HashSet<Pet> hashSet = new HashSet<Pet>(list);
		display(hashSet.iterator());
		// TreeSet<Pet> treeSet = new TreeSet<Pet>(list);
		// display(treeSet.iterator());
		LinkedHashSet<Pet> linkedHashSet = new LinkedHashSet<Pet>(list);
		display(linkedHashSet.iterator());
	}

	@Test
	public void testListIterator() {
		List<Pet> lists = Pet.arrayList(5);
		println(lists);
		ListIterator<Pet> listIterator = lists.listIterator();
		while (listIterator.hasNext()) {
			println(listIterator.previousIndex() + "," + listIterator.nextIndex() + ":" + listIterator.next());
		}
		println();
		while (listIterator.hasPrevious()) {
			println(listIterator.nextIndex() + "," + listIterator.previousIndex() + ":" + listIterator.previous());
		}
		println();
		listIterator = lists.listIterator(2);
		println(lists);
		while (listIterator.hasNext()) {
			print(listIterator.next() + " ");
		}
	}

	@Test
	public void testAdd4ListIterator() {
		List<Pet> lists = Pet.arrayList(5);
		println(lists);
		ListIterator<Pet> listIterator = lists.listIterator();
		while (listIterator.hasNext()) {
			print(listIterator.next() + " ");
			if (listIterator.nextIndex() == 3) {
				listIterator.add(new Dog("dog2"));
			}
		}
		println();
		println(lists);
	}

	public void display(Iterator<Pet> iterator) {
		while (iterator.hasNext()) {
			Pet pet = iterator.next();
			print(pet + " ");
		}
		println();
	}
}