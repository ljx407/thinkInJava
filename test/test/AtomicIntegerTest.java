package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static util.PrintUtil.println;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class AtomicIntegerTest {
	@Test
	public void testAll() throws InterruptedException {
		final AtomicInteger value = new AtomicInteger(10);
		assertEquals(value.compareAndSet(1, 2), false);
		assertEquals(value.get(), 10);
		assertTrue(value.compareAndSet(10, 3));
		assertEquals(value.get(), 3);
		value.set(0);
		//
		assertEquals(value.incrementAndGet(), 1);
		assertEquals(value.getAndAdd(2), 1);
		assertEquals(value.getAndSet(5), 3);
		assertEquals(value.get(), 5);
		//
		final int threadSize = 10;
		Thread[] ts = new Thread[threadSize];
		for (int i = 0; i < threadSize; i++) {
			ts[i] = new Thread() {
				public void run() {
					value.incrementAndGet();
				}
			};
		}
		//
		for (Thread t : ts) {
			t.start();
		}
		for (Thread t : ts) {
			t.join();
		}
		//
		assertEquals(value.get(), 5 + threadSize);
	}

	@Test
	public void testProAndCon() {
		final AtomicInteger atomicInteger = new AtomicInteger(0);
		final Integer max = 10;

		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				Thread.currentThread().setName("pro");
				while (!Thread.interrupted()) {
					int incrementAndGet = atomicInteger.incrementAndGet();
					println("{} produce {} ", Thread.currentThread().getName(), incrementAndGet);
					synchronized (atomicInteger) {
						if (incrementAndGet == max) {
							atomicInteger.notify();
							try {
								atomicInteger.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		};

		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				Thread.currentThread().setName("con");
				while (!Thread.interrupted()) {
					int decrementAndGet = atomicInteger.decrementAndGet();
					println("{} consume {} ", Thread.currentThread().getName(), decrementAndGet);
					synchronized (atomicInteger) {
						if (decrementAndGet == 0) {
							atomicInteger.notify();
							try {
								atomicInteger.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		};
	
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(r1);
		executorService.execute(r2);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executorService.shutdownNow();
	}

}
