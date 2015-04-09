package concurrency.exercise;

import static util.PrintUtil.println;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("unused")
public class Ex03 {
	public static void main(String[] args) {
//		test1();
//		println();
//		test2();
//		println();
		test3();
	}

	private static void test1() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 3; i++) {
			executorService.execute(new Test1());
		}
		executorService.shutdown();
	}

	private static void test2() {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 3; i++) {
			executorService.execute(new Test1());
		}
		executorService.shutdown();
	}

	private static void test3() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 3; i++) {
			executorService.execute(new Test1());
		}
		executorService.shutdown();
	}
}

class Test1 implements Runnable {

	private static int count = 1;
	private final int id;

	public Test1() {
		id = count++;
		println("start¡­¡­");
	}

	@Override
	public void run() {
		println("id:{}(1)", id);
		Thread.yield();
		println("id:{}(2)", id);
		Thread.yield();
		println("id:{}(3)", id);
		Thread.yield();

		println("end¡­¡­");
	}

}
