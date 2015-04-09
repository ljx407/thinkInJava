package concurrency;

import static util.PrintUtil.println;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class Concurrency_02 {

	public static void testExecutorService() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 3; i++) {
			executorService.execute(new LiftOff());
		}
		executorService.shutdown();
	}

	public static void testThread() {
		for (int i = 0; i < 3; i++) {
			new Thread(new LiftOff()).start();
		}
	}

	public static void testSingleThreadExecutor() {
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 3; i++) {
			singleThreadExecutor.execute(new LiftOff());
		}
		singleThreadExecutor.shutdown();
	}

	public static void testSubmit() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<String>> list = new ArrayList<Future<String>>();
		for (int i = 0; i < 10; i++) {
			list.add(executorService.submit(new TaskWithResult(i)));
		}
		println("===============");
		for (Future<String> future : list) {
			try {
				println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		println("+++++++++++++");
	}

	public static void testSleep() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 3; i++) {
			executorService.execute(new SleepLiftDown());
		}
	}

	public static void testDaemon() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new SleepLiftDown());
			println(thread.getName());
			thread.setDaemon(true);
			thread.start();
		}
	}

	public static void testExtendsThread() {
		for (int i = 0; i < 3; i++) {
			new SimpleThread();
		}
	}

	public static void testSimpleRunnable() {
		for (int i = 0; i < 3; i++) {
			new SimpleRunnable();
		}
	}

	public static void testSimpleThreadWithInner() {
		for (int i = 0; i < 3; i++) {
			new SimpleThreadWithInner();
		}
	}

	public static void testSimpleThreadWithInnerAnonymous() {
		for (int i = 0; i < 3; i++) {
			new SimpleThreadWithInnerAnonymous();
		}
	}

	public static void testSimpleRunnableInner() {
		for (int i = 0; i < 3; i++) {
			new SimpleRunnableInner();
		}
	}

	public static void testSimpleRunnableInnerAnonymous() {
		for (int i = 0; i < 3; i++) {
			new SimpleRunnableInnerAnonymous();
		}
	}

	@SuppressWarnings("unused")
	public static void testSleeperAndJoiner() {
		Sleeper s1 = new Sleeper("s1", 1500), s2 = new Sleeper("s2", 1500);
		Joiner j1 = new Joiner(s1, "j1"), j2 = new Joiner(s2, "j2");
		s2.interrupt();
	}

	public static void testExceptionThread() {
		try {
			// Thread t = new Thread(new ExceptionThread());
			// t.start();
			ExecutorService executorService = Executors.newCachedThreadPool();
			executorService.execute(new ExceptionThread());
		} catch (Exception e) {
			println("main");
			println(e);
		}
	}
	
	public static void testUncaughtThreadException() {
		try {
			HandlerThreadFactory threadFactory = new HandlerThreadFactory();
			ExecutorService executorService = Executors.newCachedThreadPool(threadFactory);
			executorService.execute(new ExceptionThread());
			threadFactory.getMyUn().printMessage();
		} catch (Exception e) {
			println("main");
			println(e);
		}
	}

	public static void main(String[] args) {
		 testExecutorService();

		// testThread();

		// testSingleThreadExecutor();

		// testSubmit();

		// testSleep();

		// try {
		// testDaemon();
		// TimeUnit.MILLISECONDS.sleep(5);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

		// testExtendsThread();

		// testSimpleRunnable();

		// testSimpleThreadWithInner();
		// testSimpleThreadWithInnerAnonymous();

		// testSimpleRunnableInner();
		// testSimpleRunnableInnerAnonymous();
		// testSleeperAndJoiner();
//		testUncaughtThreadException();
	}
}

class LiftOff implements Runnable {

	protected int countDown = 10;

	private static int count = 1;

	private final int id;

	public LiftOff() {
		id = count++;
	}

	public void status() {
		println("#id:{}({})", id, (countDown > 0 ? countDown : "liftoff!"));
	}

	@Override
	public void run() {
		while (countDown-- > 0) {
			status();
			Thread.yield();
		}
	}
}

class SleepLiftDown extends LiftOff {
	@Override
	public void run() {
		while (countDown-- > 0) {
			status();
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class TaskWithResult implements Callable<String> {

	private static int counter = 1;
	private int id;

	public TaskWithResult(int id) {
		this.id = counter++;
	}

	@Override
	public String call() throws Exception {
		return "TaskWithResult id " + id;
	}

}

class SimpleThread extends Thread {
	private int countDown = 5;
	private static int threadCount = 0;

	public SimpleThread() {
		super.setName("threadName:" + ++threadCount);
		start();
	}

	@Override
	public String toString() {
		return Thread.currentThread().getName() + ":" + countDown;
	}

	@Override
	public void run() {
		while (true) {
			if (countDown-- == 0) {
				return;
			}
			println(this);
		}
	}
}

class SimpleThreadWithInner {

	private static int threadCount = 0;

	public SimpleThreadWithInner() {
		Inner inner = new Inner(++threadCount + "");
		inner.start();
	}

	class Inner extends Thread {

		private int countDown = 5;

		public Inner(String name) {
			super(name);
		}

		@Override
		public void run() {
			while (true) {
				if (countDown-- == 0) {
					return;
				}
				println(Thread.currentThread().getName() + ":" + countDown);
			}
		}
	}
}

class SimpleThreadWithInnerAnonymous {

	private static int threadCount = 0;

	public SimpleThreadWithInnerAnonymous() {
		Thread t = new Thread(++threadCount + "") {

			private int countDown = 5;

			@Override
			public void run() {
				while (true) {
					if (countDown-- == 0) {
						return;
					}
					println(Thread.currentThread().getName() + ":" + countDown);
				}
			}

		};
		t.start();
	}

}

class SimpleRunnable implements Runnable {

	private static int threadCount = 0;
	private int countDown = 5;

	Thread t;

	public SimpleRunnable() {
		t = new Thread(this);
		t.setName(++threadCount + "");
		t.start();
	}

	@Override
	public void run() {
		while (true) {
			if (countDown-- == 0) {
				return;
			}
			println(this);
		}
	}

	@Override
	public String toString() {
		return "SimpleRunnable:" + Thread.currentThread().getName() + " [countDown=" + countDown + "]";
	}
}

class SimpleRunnableInner {
	private static int runnableCount = 0;
	Inner inner = null;

	public SimpleRunnableInner() {
		inner = new Inner(++runnableCount + "");
	}

	class Inner implements Runnable {

		private int countDown = 5;
		private Thread t;

		public Inner(String name) {
			t = new Thread(this, name);
			t.start();
		}

		@Override
		public void run() {
			while (true) {
				if (countDown-- == 0)
					return;
				println(Thread.currentThread().getName() + ":" + countDown);
			}
		}

	}
}

class SimpleRunnableInnerAnonymous {
	private static int runnableCount = 0;
	Thread t = null;

	SimpleRunnableInnerAnonymous() {
		t = new Thread(new Runnable() {
			private int countDown = 5;

			@Override
			public void run() {
				while (true) {
					if (countDown-- == 0)
						return;
					println(Thread.currentThread().getName() + ":" + countDown);
				}
			}

		}, ++runnableCount + "");
		t.start();
	}
}

@SuppressWarnings("unused")
class Sleeper extends Thread {

	private String name;
	private int sleepTime;

	public Sleeper(String name, int sleepTime) {
		super(name);
		this.sleepTime = sleepTime;
		start();
	}

	@Override
	public void run() {
		try {
			sleep(sleepTime);
		} catch (InterruptedException e) {
			println("thread[{}] is interrupted !", this.getName());
			return;
		}
		println("thread[{}] complete!", this.getName());
	}
}

@SuppressWarnings("unused")
class Joiner extends Thread {

	private Sleeper sleeper;
	private String name;
	private int joinTime;

	public Joiner(Sleeper sleeper, String name) {
		this(sleeper, name, 50);
	}

	public Joiner(Sleeper sleeper, String name, int joinTime) {
		super(name);
		this.sleeper = sleeper;
		this.joinTime = joinTime;
		start();
	}

	@Override
	public void run() {
		try {
			sleeper.join(joinTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		println("thread[{}] done !", this.getName());
	}

}

class ExceptionThread implements Runnable {

	@Override
	public void run() {
		throw new RuntimeException();
	}

}

class HandlerThreadFactory implements ThreadFactory {

	private MyUncaughtedException eh = null ;
	
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		eh = new MyUncaughtedException();
		t.setUncaughtExceptionHandler(eh);
		return t;
	}
	
	public MyUncaughtedException getMyUn() {
		return eh;
	}
}

class MyUncaughtedException implements UncaughtExceptionHandler {
	
	private Thread t ;
	private Throwable e ;
	
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		this.t = t;
		this.e = e ;
		println("caught : " + e);
	}
	
	public String printMessage() {
		return t.getName() + e.getMessage();
	}
	
}
