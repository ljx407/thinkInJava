package holding;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import static util.PrintUtil.*;

public class Holding11_07_linkedList {

	LinkedList<Pet> linkedList = null;

	@Before
	public void before() {
		linkedList = new LinkedList<Pet>(Pet.arrayList(6));
		println(linkedList);
	}

	@Test
	public void addTest() {
		linkedList.addFirst(new Dog("dog2"));
		println("addFirst() : {}", linkedList);

		linkedList.addLast(new Dog("dog3"));
		println("addLast() : {}", linkedList);

		linkedList.add(new Dog("dog4"));
		println("add() : {}", linkedList);
	}

	@Test
	public void offerTest() {
		linkedList.offer(new Dog("dog2"));
		println("offer() : {}", linkedList);

		linkedList.offerFirst(new Dog("dog1"));
		println("offerFirst() : {}", linkedList);

		linkedList.offerLast(new Dog("dog3"));
		println("offerLast() : {} ", linkedList);
	}

	@Test
	public void getTest() {
		println("get(0) {}", linkedList.get(0));
		println("getFirst() : {}",linkedList.getFirst());
		println("getLast() : {} ", linkedList.getLast());
		println("elemetn() {}", linkedList.element());
		println(linkedList);

	}
	
	@Test
	public void peekTest() {
		println("peek() : {} ", linkedList.peek());
		println("peekFirst() : {} ", linkedList.peekFirst());
		println("peekLast() : {} ", linkedList.peekLast());
		println(linkedList);
	}
	
	@Test
	public void poll() {
		println("poll() : {} " , linkedList.poll());
		println(linkedList);
		
		println("pollFirst() : {}", linkedList.pollFirst());
		println(linkedList);
		
		println("pollLast() : {}", linkedList.pollLast());
		println(linkedList);
	}

	@Test
	public void removeTest() {
		println("remove() : {} ", linkedList.remove());
		println(linkedList);
		
		println("removeFirst() : {} ", linkedList.removeFirst());
		println(linkedList);
		
		println("removeLast() : {} ", linkedList.removeLast());
		println(linkedList);
	}

}
