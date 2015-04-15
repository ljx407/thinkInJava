package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static util.PrintUtil.*;

public class Concurrency_07 {
	public static void main(String[] args) {
		new HorseRace(7, 200);
	}
	
	public static void testDelayQueue() {
	}
}

class Horse implements Runnable {

	private static int count = 0;
	private final int id;
	private Random random = new Random();
	private int striked = 1;
	private CyclicBarrier cyclicBarrier;

	public Horse(CyclicBarrier cyclicBarrier) {
		this.id = ++count;
		this.cyclicBarrier = cyclicBarrier;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			synchronized (this) {
				striked += random.nextInt(3);
				try {
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int getId() {
		return id;
	}

	public int getStriked() {
		return striked;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < striked; i++) {
			sb.append("*");
		}
		return sb.toString() + id;
	}
}

class HorseRace {
	private final int FINISH = 75;
	private CyclicBarrier cyclicBarrier;
	private List<Horse> horses = new ArrayList<Horse>();
	private ExecutorService executorService = Executors.newCachedThreadPool();

	public HorseRace(final int nHorse, final int pause) {

		cyclicBarrier = new CyclicBarrier(nHorse, new Runnable() {
			@Override
			public void run() {

				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < FINISH; i++) {
					sb.append("=");
				}
				println(sb.toString());

				for (Horse horse : horses) {
					println(horse.toString());
				}

				for (Horse h2 : horses) {
					if (h2.getStriked() >= FINISH) {
						executorService.shutdownNow();
						println(h2.getId() + " win !!!!!!");
						return;
					}
				}

				try {
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});

		for (int i = 0; i < nHorse; i++) {
			Horse horse = new Horse(cyclicBarrier);
			horses.add(horse);
			executorService.execute(horse);
		}
	}
}

class DelayTask implements Runnable, Delayed {

	private static int counter = 0;
	private final int id = ++counter;
	private long delayTime;
	private long submitTime;
	private static DelayQueue<DelayTask> delayQueue = new DelayQueue<DelayTask>();

	public DelayTask(int submitTime) {
		this.submitTime = submitTime;
		delayTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(submitTime, TimeUnit.MILLISECONDS);
		delayQueue.add(this);
	}

	@Override
	public void run() {
		println("{}:{} executor !", id, submitTime);
	}

	@Override
	public int compareTo(Delayed o) {
		DelayTask that = (DelayTask) o;
		if (delayTime < that.delayTime)
			return -1;
		if (delayTime > that.delayTime)
			return 1;
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(delayTime - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

}

class DelayTashCusumer implements Runnable {

	private DelayQueue<DelayTask> delayQueue;
	private ExecutorService executorService;

	public DelayTashCusumer(DelayQueue<DelayTask> delayQueue, ExecutorService executorService) {
		this.delayQueue = delayQueue;
		this.executorService = executorService;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				delayQueue.take().run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			executorService.shutdownNow();
		}
	}

}
