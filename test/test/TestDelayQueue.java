package test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static util.PrintUtil.*;

public class TestDelayQueue {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<DelayElements> blockingQueue = new DelayQueue<DelayElements>();
		blockingQueue.put(new DelayElements(500));
		blockingQueue.put(new DelayElements(1500));
		blockingQueue.put(new DelayElements(1000));
		
		while(blockingQueue.peek() != null) {
			Delayed d = blockingQueue.take();
			println(d);
		}
	}
}

class DelayElements implements Delayed {
	
	private static int counter = 0;
	private final int id = ++counter;
	private long time ;
	
	public DelayElements(long time) {
		this.time = TimeUnit.NANOSECONDS.convert(time, TimeUnit.MILLISECONDS) + System.nanoTime();
	}

	@Override
	public int compareTo(Delayed o) {
		if(o instanceof DelayElements) {
			DelayElements cast = DelayElements.class.cast(o);
			long result = this.time - cast.time;
			return result > 0 ? 1 : (result < 0 ? -1 : 0); 
		} else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(time - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	@Override
	public String toString() {
		return "DelayElements [id=" + id + "]";
	}
	
	
}
