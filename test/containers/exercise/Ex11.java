package containers.exercise;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import org.junit.Test;

import static util.PrintUtil.*;

public class Ex11 {
	
	@Test
	public void test() {
		Queue<TestPriority> queue = new PriorityQueue<TestPriority>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			queue.offer(new TestPriority(random.nextInt(100)));
		}
		while(queue.peek() != null) {
			println(queue.poll());
		}
	}
	
}

class TestPriority implements Comparable<TestPriority>{
	private Integer priority ;

	public TestPriority(Integer priority) {
		this.priority = priority;
	}

	@Override
	public int compareTo(TestPriority o) {
		return priority.compareTo(o.priority);
	}

	@Override
	public String toString() {
		return "TestPriority [priority=" + priority + "]";
	}
	
}
