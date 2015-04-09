package concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import util.Generator;

import static util.PrintUtil.*;

public class Ex02 {
	public static void main(String[] args) {

		final int n = 10;
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 3; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					Fibonacci gen = new Fibonacci();
					for (int i = 0; i < n; i++)
						println("id:{}({})", gen.getId(), gen.next());
				}
			});
		}
	}
}

class Fibonacci implements Generator<Integer> {
	private static int idGen = 1;
	private int count = 0;
	private final int id;

	public Fibonacci() {
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
