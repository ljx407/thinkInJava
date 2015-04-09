package concurrency.exercise;

import static util.PrintUtil.println;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex01 {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 3; i++) {
			executorService.execute(new Test());
		}
		executorService.shutdown();
	}
	
}

class Test implements Runnable {
	
	private static int count = 1;
	private final int id ;
	
	public Test() {
		id = count++;
		println("start¡­¡­");
	}

	@Override
	public void run() {
		println("id:{}(1)",id);
		Thread.yield();
		println("id:{}(2)",id);
		Thread.yield();
		println("id:{}(3)",id);
		Thread.yield();
		
		println("end¡­¡­");
	}
	
}

