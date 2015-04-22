package concurrent;

//: concurrency/SimpleMicroBenchmark.java
// The dangers of microbenchmarking.
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

abstract class Incrementable {
	protected long counter = 0;

	public abstract void increment();
}

class SynchronizingTest extends Incrementable {
	public synchronized void increment() {
		++counter;
	}
}

class LockingTest extends Incrementable {
	private Lock lock = new ReentrantLock();

	public void increment() {
		lock.lock();
		try {
			++counter;
		} finally {
			lock.unlock();
		}
	}
}

public class SimpleMicroBenchmark {
	static long test(Incrementable incr) {
		long start = System.nanoTime();
		for (long i = 0; i < 10000000L; i++)
			incr.increment();
		return System.nanoTime() - start;
	}

	public static void main(String[] args) {
		Map<String,Integer> map = new HashMap<String,Integer>(){
			private static final long serialVersionUID = 1L;
			{
				this.put("lock", 0);
				this.put("synchronized", 0);
			}
		};
		for (int i = 0; i < 10; i++) {
			long synchTime = test(new SynchronizingTest());
			long lockTime = test(new LockingTest());
//			System.out.printf("synchronized: %1$10d\n", synchTime);
//			System.out.printf("Lock:         %1$10d\n", lockTime);
//			System.out.printf("Lock/synchronized = %1$.3f", d);
			double d = (double) lockTime / (double) synchTime;
			if(d > 1) {
				map.put("lock", map.get("lock") + 1);
			} else {
				map.put("synchronized", map.get("synchronized") + 1);
			}
		}
		System.out.println(map);
	}
} /*
 * Output: (75% match) synchronized: 244919117 Lock: 939098964 Lock/synchronized = 3.834
 */// :~
