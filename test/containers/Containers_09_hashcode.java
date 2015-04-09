package containers;

import static util.PrintUtil.println;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class Containers_09_hashcode {
	@Test
	public void test() {
		int i = 32768;
		String s = Integer.toBinaryString(i);
		println(s);
		println(s.length());

		println(Integer.toBinaryString(i ^ ((i >>> 20) ^ (i >>> 12))));
	}

	@Test
	public void test2() throws InterruptedException, ExecutionException {
		ExecutorService exe = Executors.newCachedThreadPool();
		List<Future<String>> s = new ArrayList<Future<String>>();
		for (int i = 0; i < 5; i++) {
			s.add(exe.submit(new TestA(i)));
		}
		println("++++++++++++");
		for (Future<String> f : s) {
			println(f.get());
		}
	}

	@Test
	public void test5() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 2; i++) {
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					Singleton.getSingleton().print();
				}
			});
		}
		executorService.shutdown();
	}
}

class Singleton {
	private static int count = 0;
	private static Singleton s = null;

	private Singleton(int i) {
		count = i;
	}

	public static Singleton getSingleton() {
		if (s == null) {
			s = new Singleton(count++);
		}
		return s;
	}

	public void print() {
		System.out.println(count);
	}

	@Override
	public String toString() {
		return "Singleton []" + count;
	}
}

class TestA implements Callable<String> {
	private int id;

	public TestA(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		println("======" + id);
		return "result is : " + id;
	}

}

class SlowMap<K, V> extends AbstractMap<K, V> {
	List<K> keys = new ArrayList<K>();
	List<V> values = new ArrayList<V>();

	public SlowMap() {
		super();
	}

	@Override
	public V get(Object k) {
		if (keys.contains(k)) {
			return values.get(keys.indexOf(k));
		}
		return null;
	}

	@Override
	public V put(K k, V v) {
		if (!keys.contains(k)) {
			keys.add(k);
			values.add(v);
		} else {
			values.set(keys.indexOf(k), v);
		}
		return v;
	}

	public Set<K> keySet() {
		return new HashSet<K>(keys);
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		return null;
	}
}
