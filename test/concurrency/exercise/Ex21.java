package concurrency.exercise;

import static util.PrintUtil.println;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex21 {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Object obj = new Object();
		executorService.execute(new TestWait(obj));
		executorService.execute(new testNotify(obj));
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			println("main interruptedException");
		}
		executorService.shutdownNow();
	}
}

class TestWait implements Runnable {

	private int i = 0;
	Object obj ;

	public TestWait(Object obj) {
		this.obj = obj;
	}

	@Override
	public void run() {
		println("testWait:{}", ++i);
		try {
			synchronized(obj) {
				obj.wait();
			}
		} catch (InterruptedException e) {
			println("testwait interruptedExecption!");
		}
		println("I am back !");

	}

}

class testNotify implements Runnable {

	private Object obj;

	public testNotify(Object obj) {
		this.obj = obj;
	}

	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			println("testNotify interruptedExecption!");
		}
		println("notifyAll!");
		obj.notifyAll();
	}

}