package holding.exercise;

import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import org.junit.Test;

import static util.PrintUtil.*;

public class Ex28 {
	
	public <T> void printQueue(Queue<T> queue) {
		while(queue.peek() != null) {
			print(queue.poll() + " ");
		}
	}
	
	@Test
	public void testPriorityQueue() {
		Random random = new Random(new Date().getTime());
		Queue<Double> priorityQueue = new PriorityQueue<Double>();
		for (int i = 0; i < 20; i++) {
			priorityQueue.offer(random.nextDouble());
		}
		printQueue(priorityQueue);
	}
}
