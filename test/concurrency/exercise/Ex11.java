package concurrency.exercise;

import static util.PrintUtil.println;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex11 {
	public static void main(String[] args) {
		testModify(new Test11());
	}
	
	public static void testModify(Test11 t) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			executorService.execute(t);
		}
		try {
			executorService.awaitTermination(2, TimeUnit.SECONDS);
			executorService.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*for (int i = 0; i < 10; i++) {
			new Thread(t).start();
		}*/
		println("a={},b={}",t.getA(),t.getB());
	}
}

class Test11 implements Runnable {

	private int a;
	private int b;

	public synchronized void modify() {
		a++;
		Thread.yield();
		a++;
		b++;
		Thread.yield();
		b++;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			modify();
		}
	}
	
}
