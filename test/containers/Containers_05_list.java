package containers;

import static util.PrintUtil.print;
import static util.PrintUtil.println;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

import util.Countries;

public class Containers_05_list {

	private String s;
	private List<String> list;
	private Iterator<String> itr;
	private ListIterator<String> listItr;

	@Test
	public void basicTest() {
		list = new ArrayList<String>(Countries.names(26));
		println("orignal list:{}", list);
		list.add("one");
		list.add(0, "two");
		println("after add() list:{}", list);
		list.addAll(Countries.names(26));
		list.addAll(2, Countries.names(26));
		println("after addAll() list:{}", list);

		s = list.get(0);
		println("list:{}", list);
		println("list.get(0):{}", s);

		println("list.indexOf():{}", list.indexOf(s));
		println("list.lastIndexOf():{}", list.lastIndexOf(s));

		println("list.contains():{}", list.contains(s));
		List<String> sublist = list.subList(1, 3);
		println("list.containsAll:{}", list.containsAll(sublist));

		list.remove(s);
		println("after remove:{}", list);
		list.remove(0);
		println("after remove(index):{}", list);

		println("list.size():{}", list.size());

		itr = list.iterator();
		listItr = list.listIterator(5);
		listItr = list.listIterator();
		list.clear();
		println("list.clear():{}", list);

	}

	@Test
	public void testItrMotation() {
		list = new ArrayList<String>(Countries.names(6));
		println("original list:{}", list);
		itr = list.iterator();
		int index = 0;
		while (itr.hasNext()) {
			print("{} ", itr.next());
			if ((index++) == 0) {
				itr.remove();
			}
		}
		println();
		println("removed list:{}", list);

		listItr = list.listIterator();
		while (listItr.hasNext()) {
			print("{}-{} ", listItr.nextIndex(), listItr.next());
		}
		println();
		println("after listIterator remove : {}", list);
		while (listItr.hasPrevious()) {
			print("{} ", listItr.previous());
		}
		println();
		listItr = list.listIterator(3);
		while (listItr.hasNext()) {
			print("{} ", listItr.next());
		}
	}

}
