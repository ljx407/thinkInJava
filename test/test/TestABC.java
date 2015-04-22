package test;

import static util.PrintUtil.print;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestABC {
	private Lock lock = new ReentrantLock();
	private Condition cA = lock.newCondition();
	private Condition cB = lock.newCondition();
	private Condition cC = lock.newCondition();
	private String t = "A";
	public int size = 10;

	public void printA() {
		try {
			lock.lock();
			while (!"A".equals(t))
				cA.await();
			print("A");
			t = "B";
			cB.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void printB() {
		try {
			lock.lock();
			while (!"B".equals(t))
				cB.await();
			print("B");
			t = "C";
			cC.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void printC() {
		try {
			lock.lock();
			while (!"C".equals(t))
				cC.await();
			print("C");
			t = "A";
			// TimeUnit.MILLISECONDS.sleep(100);
			cA.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TestABC t = new TestABC();
		ExecutorService executorService = Executors.newCachedThreadPool();

		executorService.execute(new PrintA(t));
		executorService.execute(new PrintB(t));
		executorService.execute(new PrintC(t));

		executorService.shutdown();
	}
}

class PrintA implements Runnable {

	TestABC ta;

	public PrintA(TestABC t) {
		this.ta = t;
	}

	@Override
	public void run() {
		for (int i = 0; i < ta.size; i++) {
			ta.printA();
		}
	}

}

class PrintB implements Runnable {

	TestABC ta;

	public PrintB(TestABC t) {
		this.ta = t;
	}

	@Override
	public void run() {
		for (int i = 0; i < ta.size; i++) {
			ta.printB();
		}
	}

}

class PrintC implements Runnable {

	TestABC ta;

	public PrintC(TestABC t) {
		this.ta = t;
	}

	@Override
	public void run() {
		for (int i = 0; i < ta.size; i++) {
			ta.printC();
		}
	}

}
