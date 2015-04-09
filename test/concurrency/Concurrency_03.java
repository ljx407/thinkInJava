package concurrency;

import static util.PrintUtil.println;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

@SuppressWarnings("unused")
public class Concurrency_03 {

	public static void main(String[] args) {
		// EventChecked.test(new EventGenerator());
		// EventChecked.test(new SyncronizedEventGenerator());
		// EventChecked.test(new MutexEventGenerator());
		// testLock();
		// testAtomicity();
		// testSerialNumberGenrator();
		// testAtomicity2();
		// testCriticalSection();
//		testSynchObject();
		testThreadLocal();
		
	}
	
	@Test
	public void test() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new Runnable() {
			int i = 0 ;
			@Override
			public void run() {
				while(true) {
					println(++i);
				}
			}
		});
		try {
			executorService.awaitTermination(3, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executorService.shutdown();
		
	}
	
	public static void testThreadLocal() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			executorService.execute(new Accessor(i));
		}
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executorService.shutdownNow();
	}

	public static void testSynchObject() {
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				System.err.println("aborted!");
				System.exit(0);
			}

		}, 1000);

		final DualSysnc d = new DualSysnc();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					d.f();
					Thread.yield();
				}
			}

		});
		for (int i = 0; i < 5; i++) {
			d.g();
			Thread.yield();
		}
		executorService.shutdown();

	}

	public static void testCriticalSection() {
		PairManager p1 = new PairManager1();
		PairManager p2 = new PairManager2();
		CriticalSection.test(p1, p2);
	}

	private static void testSerialNumberGenrator() {
		int size = 10;
		ExecutorService executorService = Executors.newCachedThreadPool();
		SerialChecked checked = new SerialChecked();
		for (int i = 0; i < size; i++) {
			executorService.execute(checked);
		}
		executorService.shutdown();
		try {
			TimeUnit.SECONDS.sleep(10);
			println("no duplicate number exist !");
			System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void testAtomicity() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Atomicity at = new Atomicity();
		// for (int i = 0; i < 5; i++) {
		executorService.execute(at);
		// }
		// try {
		// TimeUnit.MILLISECONDS.sleep(10);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		while (true) {
			int value = at.getValue();
			if (value % 2 != 0) {
				println(value);
				System.exit(0);
			}
		}
	}

	private static void testAtomicity2() {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.err.println("aborted!");
				System.exit(0);
			}

		}, 5000);
		ExecutorService executorService = Executors.newCachedThreadPool();
		Atomicity2 at = new Atomicity2();
		executorService.execute(at);
		while (true) {
			int value = at.getValue();
			if (value % 2 != 0) {
				println(value);
				System.exit(0);
			}
		}
	}

	private static void testLock() {
		final AttempLock lock = new AttempLock();
		lock.untimed();
		lock.timed();
		new Thread() {
			{
				setDaemon(true);
			}

			@Override
			public void run() {
				lock.reentrantLock.lock();
				println("demon start !");
			}

		}.start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.untimed();
		lock.timed();
	}
}

abstract class IntGenerator {
	private boolean canceled = false;

	abstract int next();

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public void canceled() {
		canceled = true;
	}

}

class EventGenerator extends IntGenerator {

	private volatile int num;

	@Override
	int next() {
		num++;
		Thread.yield();
		num++;
		return num;
	}

}

class SyncronizedEventGenerator extends IntGenerator {

	private volatile int num;

	@Override
	public synchronized int next() {
		num++;
		Thread.yield();
		num++;
		return num;
	}
}

class MutexEventGenerator extends IntGenerator {

	private volatile int num;
	private Lock lock = new ReentrantLock();

	@Override
	public int next() {
		try {
			lock.lock();
			num++;
			Thread.yield();
			num++;
			return num;
		} finally {
			lock.unlock();
		}
	}
}

class EventChecked implements Runnable {

	private IntGenerator intGenerator;
	private final int id;

	public EventChecked(IntGenerator intGenerator, int id) {
		this.intGenerator = intGenerator;
		this.id = id;
	}

	@Override
	public void run() {
		while (!intGenerator.isCanceled()) {
			int next = intGenerator.next();
			println("intGenerator[{}]:{}", id, next);
			if (next % 2 != 0) {
				println("intGenerator[{}] canceled!", id);
				intGenerator.canceled();
				println("thread count:{}", Thread.activeCount());
			}
		}
	}

	public static void test(IntGenerator intGenerator) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			executorService.execute(new EventChecked(intGenerator, i));
		}
		executorService.shutdown();
		// try {
		// executorService.awaitTermination(10, TimeUnit.SECONDS);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
	}

}

class AttempLock {
	public ReentrantLock reentrantLock = new ReentrantLock();

	public void untimed() {
		boolean captcher = false;
		try {
			captcher = reentrantLock.tryLock();
			if (captcher) {
				println("untimed captcher !");
			} else {
				println("untimed failure !");
			}
		} finally {
			if (captcher)
				reentrantLock.unlock();
		}
	}

	public void timed() {
		boolean captcher = false;
		try {
			captcher = reentrantLock.tryLock(2, TimeUnit.SECONDS);
			if (captcher) {
				println("timed captcher !");
			} else {
				println("timed failure !");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (captcher)
				reentrantLock.unlock();
		}
	}

}

class Atomicity2 implements Runnable {
	private AtomicInteger i = new AtomicInteger(0);

	public int getValue() {
		println(i);
		return i.get();
	}

	public void evenIncrement() {
		i.addAndGet(2);
	}

	@Override
	public void run() {
		while (true) {
			evenIncrement();
		}
	}
}

class Atomicity implements Runnable {

	private volatile int i = 0;

	public synchronized int getValue() {
		println(i);
		return i;
	}

	public synchronized void evenIncrement() {
		i++;
		i++;
	}

	@Override
	public void run() {
		while (true) {
			evenIncrement();
		}
	}

}

class SerialNumberGenerator {
	private volatile static int serialNumber = 0;

	public static synchronized int getNext() {
		return serialNumber++;
	}
}

class SerialChecked implements Runnable {

	private int len = 1000;
	private Integer[] serials;
	private int index = 0;

	public SerialChecked() {
		serials = Collections.nCopies(len, -1).toArray(new Integer[0]);
		println(Arrays.toString(serials));
	}

	public synchronized void add(int serialNum) {
		serials[index] = serialNum;
		index = ++index % len;
	}

	public synchronized boolean contains(int serialNum) {
		List<Integer> list = Arrays.asList(serials);
		return list.contains(serialNum);
	}

	@Override
	public void run() {
		while (true) {
			int next = SerialNumberGenerator.getNext();
			if (contains(next)) {
				println("{},duplicate:{}", Thread.currentThread().getName(), next);
				System.exit(0);
			}
			println("{}:{}", Thread.currentThread().getName(), next);
			add(next);
		}
	}
}

class Pair {
	private int x;
	private int y;

	public Pair() {
	}

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public void incrementX() {
		x++;
	}

	public void incrementY() {
		y++;
	}

	public class PairValueNotEqualsException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public PairValueNotEqualsException() {
			super();
		}

		public PairValueNotEqualsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}

		public PairValueNotEqualsException(String message, Throwable cause) {
			super(message, cause);
		}

		public PairValueNotEqualsException(String message) {
			super(message);
		}

		public PairValueNotEqualsException(Throwable cause) {
			super(cause);
		}
	}

	public void statusCheck() {
		if (x != y) {
			throw new PairValueNotEqualsException("数值不一致！");
		}
	}

	@Override
	public String toString() {
		return "Pair [x=" + x + ", y=" + y + "]";
	}
}

abstract class PairManager {
	AtomicInteger atomicInteger = new AtomicInteger(0);
	protected Pair p = new Pair();

	public PairManager() {
	}

	protected List<Pair> pairs = Collections.synchronizedList(new ArrayList<Pair>());

	public synchronized Pair getPair() {
		return new Pair(p.getX(), p.getY());
	}

	public synchronized void store(Pair pair) {
		pairs.add(pair);
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected abstract void increment();
}

class PairManager1 extends PairManager {

	@Override
	protected synchronized void increment() {
		p.incrementX();
		p.incrementY();
		store(getPair());
	}

}

class PairManager2 extends PairManager {

	@Override
	protected void increment() {
		Pair temp;
		synchronized (this) {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		}
		store(temp);
	}

}

class PairManipulator implements Runnable {

	private PairManager pairManger;

	public PairManipulator(PairManager pairManger) {
		this.pairManger = pairManger;
	}

	@Override
	public void run() {
		while (true) {
			pairManger.increment();
		}
	}

	@Override
	public String toString() {
		return "Pair:" + pairManger.getPair() + "counter:" + pairManger.atomicInteger.get();
	}

}

class PairChecked implements Runnable {

	private PairManager pairManger;

	public PairChecked(PairManager pairManger) {
		this.pairManger = pairManger;
	}

	@Override
	public void run() {
		while (true) {
			pairManger.getPair().statusCheck();
			pairManger.atomicInteger.getAndIncrement();
		}
	}

	@Override
	public String toString() {
		return "PairChecked [pairManger=" + pairManger + "]";
	}

}

class CriticalSection {
	public static void test(PairManager p1, PairManager p2) {
		final PairManipulator pm1 = new PairManipulator(p1);
		final PairManipulator pm2 = new PairManipulator(p2);
		PairChecked pc1 = new PairChecked(p1);
		PairChecked pc2 = new PairChecked(p2);

		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(pm1);
		executorService.execute(pm2);
		executorService.execute(pc1);
		executorService.execute(pc2);

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				println();
				System.err.println("aborted!");
				println("pm1:{},pm2:{}", pm1, pm2);
				System.exit(0);
			}
		}, 5000);
	}
}

@SuppressWarnings("unused")
class DualSysnc {
	private Object syncObject = new Object();

	public synchronized void f() {
		println("f()");
	}

	public void g() {
		synchronized (this) {
			println("g()");
		}
	}
}

class Accessor implements Runnable {
	
	private final int id ;
	
	public Accessor(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariable.increment();
			println(this);
			Thread.yield();
		}
	}

	@Override
	public String toString() {
		return id + ":" + ThreadLocalVariable.get();
	}
	
}

class ThreadLocalVariable {
	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
		private Random random = new Random(47);
		
		@Override
		protected Integer initialValue() {
			return random.nextInt(10000);
		}
		
	};
	public static Integer get() {
		return threadLocal.get();
	}
	public static void increment() {
		threadLocal.set(get() + 1);
	}
}
