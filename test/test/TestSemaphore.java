package test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static util.PrintUtil.*;

public class TestSemaphore {
	static int counter = 1;

	public static void main(String[] args) {
		final Semaphore semaphore = new Semaphore(5);
		final ExecutorService executorService = Executors.newCachedThreadPool();
		final Random random = new Random();

		for (int i = 0; i < 20; i++) {
			executorService.execute(new Runnable() {
				final int NO = counter++;

				@Override
				public void run() {
					try {
						semaphore.acquire();
						println("{} get accessor !", NO);
						TimeUnit.MILLISECONDS.sleep((int) (random.nextDouble() * 1000));
						semaphore.release();
						println("{} release !", NO);
						println("----------{} available !", semaphore.availablePermits());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executorService.shutdownNow();
	}
}
