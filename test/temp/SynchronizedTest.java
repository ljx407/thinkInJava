package temp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedTest {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new Thread1());
		executorService.shutdown();
	}
}

class A {
	static synchronized void a1() {
		while(true) {
			Thread.yield();
			System.out.println("a1");
		}
	}
	
	static synchronized void a2() {
		Thread.yield();
		System.out.println("a2");
	}
}

class Thread1 implements Runnable {

	@Override
	public void run() {
//		while(true) {
			A.a1();
			A.a2();
//		}
	}
	
}

