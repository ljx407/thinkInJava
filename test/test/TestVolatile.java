package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestVolatile {
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					Test1.one();
				}
			}
		});
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					Test1.two();
				}
			}
		});
		executorService.shutdown();
		while(Thread.activeCount() != 1) {
			Thread.yield();
		}
		Test.two();
	}

	static class Test {
		static int i = 0, j = 0;
		static void one() {
			i++;
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			j++;
		}
		static void two() {
			System.out.println("i=" + i + " j=" + j);
		}
	}
	
	static class Test1 {
		static volatile int i = 0, j = 0;
		static void one() {
			i++;
			j++;
		}
		static void two() {
			System.out.println("i=" + i + " j=" + j);
		}
	}
}

