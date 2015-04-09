package concurrency.exercise;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import util.Generator;

public class Ex05 {
	public static void test1() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		Future<Integer> sum = executorService.submit(new Callable<Integer>(){

			@Override
			public Integer call() throws Exception {
				Fibonacci2 fib = new Fibonacci2();
				int result = 0;
				TimeUnit.SECONDS.sleep(4);
				for (int i = 0; i < 20; i++) {
					result += fib.next();
				}
				return result;
			}
			
		});
		
		System.out.println("=======");
		try {
			System.out.println(sum.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("=======");
		
	}
	
	public static void main(String[] args) {
		test1();
	}
}

class Fibonacci2 implements Generator<Integer> {
	private static int idGen = 1;
	private int count = 0;
	private final int id;

	public Fibonacci2() {
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
