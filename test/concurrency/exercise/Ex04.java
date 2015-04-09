package concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import util.Generator;

import static util.PrintUtil.*;

public class Ex04 {
	public static void test1() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 3; i++) {
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					Fibonacci1 fibonacci1 = new Fibonacci1();
					for (int j = 0; j < 10; j++) {
						println("id:{}({})",fibonacci1.getId(),fibonacci1.next());
					}
				}
			});
		}
	}
	
	public static void main(String[] args) {
		test1();
	}
}

class Fibonacci1 implements Generator<Integer> {
	private static int idGen = 1;
	private int count = 0;
	private final int id;

	public Fibonacci1() {
		id = idGen++;
	}

	public int getId() {
		return id;
	}

	public Integer next() {
		return fib(count++);
	}

	private int fib(int n) {
		if (n < 2)
			return 1;
		return fib(n - 2) + fib(n - 1);
	}

}