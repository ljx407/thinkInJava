package containers;

import static util.PrintUtil.print;
import static util.PrintUtil.println;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import org.junit.Test;

import util.Generator;

public class Containers_07_queue {

	private static final int count = 10;

	private <T> void fill(Queue<T> q, Generator<T> gen) {
		for (int i = 0; i < count; i++) {
			q.offer(gen.next());
		}
		while (q.peek() != null) {
			print(q.remove() + " ");
		}
		println();
	}

	@Test
	public void testQueueBehavior() {
		StringGen gen = new StringGen();
		fill(new LinkedList<String>(), gen);
		fill(new PriorityQueue<String>(), gen);
		fill(new ArrayBlockingQueue<String>(count), gen);
		fill(new LinkedBlockingQueue<String>(), gen);
		fill(new PriorityBlockingQueue<String>(), gen);
		fill(new ConcurrentLinkedDeque<String>(), gen);

	}

	@Test
	public void testTreeMap() {
		// Map<Integer,String> treeMap = new TreeMap<Integer,String>(new Comparator<Integer>() {
		//
		// @Override
		// public int compare(Integer o1, Integer o2) {
		// return o2.compareTo(o1);
		// }
		//
		// });
		Map<Integer, String> treeMap = new TreeMap<Integer, String>(Collections.reverseOrder());
		treeMap.put(1, "1");
		treeMap.put(3, "3");
		treeMap.put(2, "2");
		println(treeMap);

		test("a");
	}

	public static String test(String a) {
		try {
			return a;
		} catch (Exception e) {
		} finally {
			a = "b";
			println("finally");
		}
		println("...........");
		return "ljx";
	}

	@Test
	public void testToDoList() {
		ToDoList list = new ToDoList();
		list.add(list.new ToDoItem('A', 2, "one"));
		list.add(list.new ToDoItem('A', 1, "two"));
		list.add(list.new ToDoItem('B', 1, "three"));
		list.add(list.new ToDoItem('B', 3, "three"));
		list.add(list.new ToDoItem('C', 4, "four"));
		println(list);

		while (!list.isEmpty()) {
			println(list.poll());
		}
	}

	class ToDoList extends PriorityQueue<ToDoList.ToDoItem> {

		private static final long serialVersionUID = 1L;

		class ToDoItem implements Comparable<ToDoItem> {

			private Character first;
			private Integer secondary;
			private String item;

			public ToDoItem(Character first, Integer secondary, String item) {
				this.first = first;
				this.secondary = secondary;
				this.item = item;
			}

			@Override
			public int compareTo(ToDoItem o) {
				if (first.compareTo(o.first) > 0) {
					return 1;
				} else if (first.compareTo(o.first) == 0) {
					if (secondary.compareTo(o.secondary) > 0) {
						return 1;
					} else if (secondary.compareTo(o.secondary) == 0) {
						return 0;
					}
				}
				return -1;
			}

			@Override
			public String toString() {
				return "ToDoItem [first=" + first + ", secondary=" + secondary + ", item=" + item + "]";
			}
		}
	}

	class StringGen implements Generator<java.lang.String> {

		private String[] ss = "one two three four five six seven eight nine ten".split(" ");

		private int index;

		@Override
		public String next() {
			if (index == ss.length)
				index = 0;
			return ss[index++];
		}

	}

	public static void main(String[] args) {
		println(test("a"));
	}
}
