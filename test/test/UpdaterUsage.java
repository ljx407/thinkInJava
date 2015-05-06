package test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

class Container {
	public volatile int no;
}

class Task extends Thread {
	private AtomicIntegerFieldUpdater<Container> updater = AtomicIntegerFieldUpdater.newUpdater(Container.class, "no");
	private Container c;

	public Task(Container c) {
		this.c = c;
	}

	@Override
	public void run() {
		System.out.println(updater.getAndIncrement(c));
		System.out.println(updater.getAndIncrement(c));
	}
}

public class UpdaterUsage {
	public static void main(String[] args) {
		Container c = new Container();
		Task t1 = new Task(c);
		Task t2 = new Task(c);
		t1.start();
		t2.start();
	}
}
