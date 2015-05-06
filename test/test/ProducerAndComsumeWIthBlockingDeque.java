package test;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static util.PrintUtil.*;

public class ProducerAndComsumeWIthBlockingDeque {
	
	private BlockingDeque<String> blockingDeque = new LinkedBlockingDeque<String>();

	class Producer implements Runnable {
		int count = 0;

		@Override
		public void run() {
			while(!Thread.interrupted()) {
				synchronized(this) {
					try {
						blockingDeque.putFirst(count++ + "");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	class Consumer implements Runnable {

		@Override
		public void run() {
			try {
				while(!Thread.interrupted()) {
					String take = blockingDeque.take();
					println(take);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		ProducerAndComsumeWIthBlockingDeque pc = new ProducerAndComsumeWIthBlockingDeque();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(pc.new Producer());
		executorService.execute(pc.new Consumer());
		TimeUnit.SECONDS.sleep(4);
		executorService.shutdownNow();
//		executorService.shutdown();
	}
}

