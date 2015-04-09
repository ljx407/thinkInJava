package concurrency.exercise;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static util.PrintUtil.*;

public class Ex06 {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 4; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					Random random = new Random();
					try {
						int nextInt = random.nextInt(10);
						println(nextInt);
						TimeUnit.SECONDS.sleep(nextInt);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		executorService.shutdown();
	}
}
