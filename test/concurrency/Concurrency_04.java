package concurrency;

import static util.PrintUtil.println;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Concurrency_04 {

	public static void main(String[] args) {
		// testEntrance();
		// testBlock(new SleepBlock());
		// testBlock(new IOBlock(System.in));
		// testBlock(new SynchronizedBlock());
		// testBlock2(new IOBlock(System.in));
		testMutilBlock();
	}

	public static void testMutilBlock() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				MutilLock ml = new MutilLock();
				ml.f1(10);
			}
		});
		executorService.shutdown();
	}

	public static void testBlock2(Runnable r) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(r);
		try {
			TimeUnit.MILLISECONDS.sleep(100);
			executorService.shutdownNow();
			TimeUnit.MILLISECONDS.sleep(100);
			System.in.close();
			if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
				println("not all task terminate!");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testBlock(Runnable r) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		// executorService.execute(r);
		Future<?> submit = executorService.submit(r);
		try {
			TimeUnit.MILLISECONDS.sleep(50);
			// executorService.shutdownNow();
			executorService.shutdown();
			submit.cancel(true);
			if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
				println("not all task is terminated !");
				System.exit(0);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void testEntrance() {
		ExecutorService executorServive = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			executorServive.execute(new Entrance(i));
		}
		try {
			TimeUnit.SECONDS.sleep(3);
			Entrance.cancel();
			executorServive.shutdown();
			if (!executorServive.awaitTermination(200, TimeUnit.MILLISECONDS)) {
				println("not all task closed!");
			}
			println("all entrace : {}", Entrance.sumEntrance());
			println("all sum : {}", Count.getValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Entrance implements Runnable {

	static List<Entrance> list = new ArrayList<Entrance>();
	private static boolean canceled = false;
	private int number = 0;
	private final int id;

	public Entrance(int id) {
		this.id = id;
		list.add(this);
	}

	public void entrance() {
		number++;
		Count.increment();
	}

	public static void cancel() {
		canceled = true;
	}

	public int getValue() {
		return number;
	}

	public static int sumEntrance() {
		int sum = 0;
		if (!list.isEmpty()) {
			for (Entrance entrance : list) {
				sum += entrance.getValue();
			}
		}
		return sum;
	}

	@Override
	public void run() {
		while (!canceled) {
			entrance();
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		println(this);
	}

	@Override
	public String toString() {
		return "Entrance[id=" + id + "] entrance : " + getValue();
	}

}

class Count {
	private static int sum = 0;

	public static synchronized void increment() {
		int temp = sum;
		Thread.yield();
		sum = temp + 1;
	}

	public static synchronized int getValue() {
		return sum;
	}
}

class SleepBlock implements Runnable {
	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			println("sleepBlock interruptedException!");
		}
		println("exit sleepBlock.run()");
	}
}

class IOBlock implements Runnable {

	private InputStream inputStream;

	public IOBlock(InputStream is) {
		inputStream = is;
	}

	@Override
	public void run() {
		try {
			println("wait for read()");
			inputStream.read();
		} catch (IOException e) {
			if (Thread.currentThread().isInterrupted()) {
				println("IOBlock interrrupted!");
			} else {
				throw new RuntimeException();
			}
		}
		println("exit IOBlock.run()");
	}
}

class SynchronizedBlock implements Runnable {

	public SynchronizedBlock() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				f();
			}
		}).start();
	}

	public synchronized void f() {
		while (true) {
			Thread.yield();
		}
	}

	@Override
	public void run() {
		println("trying to call f()");
		f();
		println("exit SynchronizedBlock !");
	}

}

class MutilLock {
	public synchronized void f1(int count) {
		if (count-- > 0) {
			println("f1:{}", count);
			f2(count);
		}
	}

	public synchronized void f2(int count) {
		if (count-- > 0) {
			println("f2:{}", count);
			f1(count);
		}
	}
}

class CleanUp {
	final int id;

	public CleanUp(int id) {
		this.id = id;
	}

	public void clean() {
		println("CleanUp :" + id + " clean !");
	}
}

class Block3 implements Runnable {

	@Override
	public void run() {
//		try {
//			while(!Thread.interrupted()) {
//				 
//			}
//		} catch(InterruptedExecption ie) {
//			println("catch interruptedExecption ");
//		}
	}
	
}
