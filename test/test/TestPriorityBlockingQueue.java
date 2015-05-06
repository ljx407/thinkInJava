package test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import static util.PrintUtil.*;

public class TestPriorityBlockingQueue {

	static Random r = new Random(47);

	public static void main(String args[]) {
		final PriorityBlockingQueue<PriorityEntity> q = new PriorityBlockingQueue<PriorityEntity>();
		ExecutorService se = Executors.newCachedThreadPool();
		// execute producer
		se.execute(new Runnable() {
			public void run() {
				int i = 0;
				while (true) {
					q.put(new PriorityEntity(r.nextInt(10), i++));
					try {
						TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		// execute consumer
		se.execute(new Runnable() {
			public void run() {
				while (true) {
					try {
						println("take-- " + q.take() + " left:-- [" + q.toString() + "]");
						try {
							TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		println("shutdown");
	}
}

class PriorityEntity implements Comparable<PriorityEntity> {
	private static int count = 0;
	private int id = count++;
	private int priority;
	private int index = 0;

	public PriorityEntity(int _priority, int _index) {
		this.priority = _priority;
		this.index = _index;
	}

	public String toString() {
		return id + "# [index=" + index + " priority=" + priority + "]";
	}

	// 数字小，优先级高
	public int compareTo(PriorityEntity o) {
		return this.priority > o.priority ? 1 : this.priority < o.priority ? -1 : 0;
	}

	// 数字大，优先级高
	// public int compareTo(PriorityTask o) {
	// return this.priority < o.priority ? 1
	// : this.priority > o.priority ? -1 : 0;
	// }

}
