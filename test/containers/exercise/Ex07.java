package containers.exercise;

import static util.PrintUtil.print;
import static util.PrintUtil.println;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;

import util.Countries;

public class Ex07 {

	private List<String> arrayList, linkedList;

	@Before
	public void before() {
		arrayList = new ArrayList<String>(Countries.names(6));
		linkedList = new LinkedList<String>(Countries.names(6));
	}

	@Test
	public void testList() {
		Iterator<String> iterator = arrayList.iterator();
		while (iterator.hasNext()) {
			print("{} ", iterator.next());
		}

		println();

		Iterator<String> iterator1 = linkedList.iterator();
		while (iterator1.hasNext()) {
			print("{} ", iterator1.next());
		}
	}

	@Test
	public void testListIterator() {
		println("original list:{}", arrayList);
		List<String> insertList = new ArrayList<String>();
		ListIterator<String> listIterator = arrayList.listIterator();
		while (listIterator.hasNext()) {
			int nextIndex = listIterator.nextIndex();
			String next = listIterator.next();
			if (nextIndex % 2 == 0) {
				insertList.add(next);
			}
		}
		println("after filter : {}", insertList);
	}

	@Test
	public void testListIteratorReverse() {
		println("original list:{}", linkedList);
		ListIterator<String> listIterator = linkedList.listIterator(linkedList.size());
		List<String> filterList = new LinkedList<String>();
		while (listIterator.hasPrevious()) {
			int currentIndex = listIterator.previousIndex();
			String previous = listIterator.previous();
			if (currentIndex % 2 == 0) {
				filterList.add(previous);
			}
		}
		println("after filter : {}", filterList);
	}
}
