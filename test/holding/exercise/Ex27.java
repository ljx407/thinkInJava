package holding.exercise;

import static util.PrintUtil.println;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class Ex27 {
	
	@Test
	public void testQueue() {
		Queue<Command> queue = QueueFactory.newInstance().createQueue();
		while(queue.peek() != null) {
			Command command = queue.poll();
			command.operation();
		}
	}
}

class Command {
	private String operation;

	public Command(String operation) {
		super();
		this.operation = operation;
	}

	public void operation() {
		println(operation);
	}
}

class QueueFactory {

	private static QueueFactory queueFactory = null;

	private QueueFactory() {
		super();
	}

	public static QueueFactory newInstance() {
		return queueFactory == null ? new QueueFactory() : queueFactory;
	}

	public Queue<Command> createQueue() {
		String operation = "operation";
		Queue<Command> queue = new LinkedList<Command>();
		for (int i = 0; i < 20; i++) {
			queue.offer(new Command(operation + i));
		}
		return queue;
	}

}
