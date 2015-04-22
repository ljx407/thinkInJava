package simulation;

import java.util.LinkedList;
import java.util.Queue;

public class MyBlockingQueue<T> {
	private int limit ;
	private Queue<T> queue = new LinkedList<T>();

	public MyBlockingQueue(int limit) {
		this.limit = limit;
	}
	
	public synchronized T enqueue(T t) throws InterruptedException {
		if(queue.size() == limit) {
			notifyAll();
			while(queue.size() == limit) {
				wait();
			}
		}
		queue.add(t);
		return t;
	}
	
	public synchronized void dequeue() throws InterruptedException {
		if(queue.size() == 0) {
			notifyAll();
			while(queue.size() == 0) {
				wait();
			}
		}
		queue.remove();
	}
}
