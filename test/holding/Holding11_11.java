package holding;

import static util.PrintUtil.print;
import static util.PrintUtil.println;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class Holding11_11 {

	@Test
	public void testQueue() {
		Random random = new Random(new Date().getTime());
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < 20; i++) {
			queue.offer(random.nextInt(50));
		}
		printQueue2(queue);

		Queue<Character> characterQueue = new LinkedList<Character>();
		for (Character c : "hellojasonliu".toCharArray()) {
			characterQueue.offer(c);
		}
		printQueue1(characterQueue);
	}
	
	@Test
	public void testPriorityQueue4Integer() {
		Queue<Integer> priorityQueue = new PriorityQueue<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();

		Random random = new Random(693);
		for (int i = 0; i < 20; i++) {
			Integer num = random.nextInt(50);
			priorityQueue.offer(num);
			queue.offer(num);
		}
		printQueue(priorityQueue);
		printQueue(queue);

		List<Integer> init = Arrays.asList(1, 3, 5, 6, 7, 8, 21, 345, 56, 23, 568, 67, 32, 8, 54);
		queue = new PriorityQueue<Integer>(init);
		printQueue(queue);

		queue = new PriorityQueue<Integer>(init.size(), Collections.reverseOrder());
		queue.addAll(init);
		printQueue(queue);
	}

	@Test
	public void testPriorityQueue4String() {

		String str = "hello jason liu stay hungry stay foolish";
		List<String> init = Arrays.asList(str.split(" "));
		Queue<String> priorityQueue = new PriorityQueue<String>(init);
		// Collections.addAll(priorityQueue1, str.split(" "));

		printQueue(priorityQueue);

		priorityQueue = new PriorityQueue<String>(init.size(), Collections.reverseOrder());
		priorityQueue.addAll(init);

		printQueue(priorityQueue);

	}

	@Test
	public void testPriorityQueue4Character() {
		String str = "hello jason liu stay hungry stay foolish";
		Set<Character> init = new LinkedHashSet<Character>();
		for (char c : str.toCharArray()) {
			init.add(c);
		}

		Queue<Character> priorityQueue = new PriorityQueue<Character>(init);
		printQueue(priorityQueue);

		priorityQueue = new PriorityQueue<Character>(init.size(), Collections.reverseOrder());
		priorityQueue.addAll(init);
		printQueue(priorityQueue);
	}

	private <T> void printQueue(Queue<T> queue) {
		T t = null;
		while ((t = queue.poll()) != null) { // !queue.isEmpty()
			print(t + " ");
		}
		println("");
	}

	private <T> void printQueue1(Queue<T> queue) {
		while (queue.peek() != null) {
			print(queue.poll() + " ");
		}
		println("");
	}
	
	private <T> void printQueue2(Queue<T> queue) {
		while (!queue.isEmpty()) {
			print(queue.remove() + " ");
		}
		println("");
	}

}
