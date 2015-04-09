package holding.exercise;

import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Test;
import static util.PrintUtil.*;

public class Ex29 {
	
	@Test
	public void testPriorityQueue() {
		Queue<TestObject> priorityQueue = new PriorityQueue<TestObject>();
		priorityQueue.offer(new TestObject());
		priorityQueue.offer(new TestObject());
		println(priorityQueue);
	}
}

class TestObject {}