package test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static util.PrintUtil.*;

public class TestBlockingQueue {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(1024);
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new Producer(arrayBlockingQueue));
		executorService.execute(new Consumer(arrayBlockingQueue));
		TimeUnit.SECONDS.sleep(2);
		executorService.shutdown();
	}
}

class Producer implements Runnable {
	
	private BlockingQueue<String> arrayBlockingQueue ;
	
	public Producer(BlockingQueue<String> arrayBlockingQueue) {
		this.arrayBlockingQueue = arrayBlockingQueue;
	}

	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(100);
			arrayBlockingQueue.put("1");
			TimeUnit.MILLISECONDS.sleep(100);
			arrayBlockingQueue.put("2");
			TimeUnit.MILLISECONDS.sleep(100);
			arrayBlockingQueue.put("3");
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Consumer implements Runnable {
	
	private BlockingQueue<String> arrayBlockingQueue ;
	
	public Consumer(BlockingQueue<String> arrayBlockingQueue) {
		this.arrayBlockingQueue = arrayBlockingQueue;
	}

	@Override
	public void run() {
		try {
			println(arrayBlockingQueue.take());
			println(arrayBlockingQueue.take());
			println(arrayBlockingQueue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
 