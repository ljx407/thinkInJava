package test;

import static util.PrintUtil.println;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class TestLinkedBlockingQueue {

	public static void main(String[] args) {
		TestLinkedBlockingQueue lbq = new TestLinkedBlockingQueue();
		BlockingQueue<String> linkedQueue = new LinkedBlockingQueue<String>(10);
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(lbq.new Producer(linkedQueue));
		executorService.execute(lbq.new Producer(linkedQueue));
		executorService.execute(lbq.new Producer(linkedQueue));
		executorService.execute(lbq.new Consumer(linkedQueue));
		executorService.shutdown();
	}

	class Consumer implements Runnable {

		private BlockingQueue<String> linkedBlockingQueue;

		public Consumer(BlockingQueue<String> linkedBlockingQueue) {
			this.linkedBlockingQueue = linkedBlockingQueue;
		}

		@Override
		public void run() {
			while(!Thread.interrupted()) {
				try {
					String str = linkedBlockingQueue.take();
					println("consume element {}", str);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class Producer implements Runnable {
		private BlockingQueue<String> linkedBlockingQueue;

		public Producer(BlockingQueue<String> linkedBlockingQueue) {
			this.linkedBlockingQueue = linkedBlockingQueue;
		}

		@Override
		public void run() {
			for (int i = 0;; i++) {
				try {
					println("produce element {}", i);
					linkedBlockingQueue.put(i + "");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
