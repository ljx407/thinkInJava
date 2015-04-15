package concurrent;

//: concurrency/ToastOMatic.java
// A toaster that uses queues.
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.PrintUtil.*;

class Toast {
	public enum Status {
		DRY, BUTTERED, JAMMED
	}

	private Status status = Status.DRY;
	private final int id;

	public Toast(int idn) {
		id = idn;
	}

	public void butter() {
		status = Status.BUTTERED;
	}

	public void jam() {
		status = Status.JAMMED;
	}

	public Status getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return "Toast " + id + ": " + status;
	}
}

@SuppressWarnings("serial")
class ToastQueue extends LinkedBlockingQueue<Toast> {
}

class Toaster implements Runnable {
	private ToastQueue toastQueue;
	private int count = 0;
	private Random rand = new Random(47);

	public Toaster(ToastQueue tq) {
		toastQueue = tq;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
				// Make toast
				Toast t = new Toast(count++);
				println(t);
				// Insert into queue
				toastQueue.put(t);
			}
		} catch (InterruptedException e) {
			println("Toaster interrupted");
		}
		println("Toaster off");
	}
}

// Apply butter to toast:
class Butterer implements Runnable {
	private ToastQueue dryQueue, butteredQueue;

	public Butterer(ToastQueue dry, ToastQueue buttered) {
		dryQueue = dry;
		butteredQueue = buttered;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				// Blocks until next piece of toast is available:
				Toast t = dryQueue.take();
				t.butter();
				println(t);
				butteredQueue.put(t);
			}
		} catch (InterruptedException e) {
			println("Butterer interrupted");
		}
		println("Butterer off");
	}
}

// Apply jam to buttered toast:
class Jammer implements Runnable {
	private ToastQueue butteredQueue, finishedQueue;

	public Jammer(ToastQueue buttered, ToastQueue finished) {
		butteredQueue = buttered;
		finishedQueue = finished;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				// Blocks until next piece of toast is available:
				Toast t = butteredQueue.take();
				t.jam();
				println(t);
				finishedQueue.put(t);
			}
		} catch (InterruptedException e) {
			println("Jammer interrupted");
		}
		println("Jammer off");
	}
}

// Consume the toast:
class Eater implements Runnable {
	private ToastQueue finishedQueue;
	private int counter = 0;

	public Eater(ToastQueue finished) {
		finishedQueue = finished;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				// Blocks until next piece of toast is available:
				Toast t = finishedQueue.take();
				// Verify that the toast is coming in order,
				// and that all pieces are getting jammed:
				if (t.getId() != counter++ || t.getStatus() != Toast.Status.JAMMED) {
					println(">>>> Error: " + t);
					System.exit(1);
				} else
					println("Chomp! " + t);
			}
		} catch (InterruptedException e) {
			println("Eater interrupted");
		}
		println("Eater off");
	}
}

public class ToastOMatic {
	public static void main(String[] args) throws Exception {
		ToastQueue dryQueue = new ToastQueue(), butteredQueue = new ToastQueue(), finishedQueue = new ToastQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue, butteredQueue));
		exec.execute(new Jammer(butteredQueue, finishedQueue));
		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
} /* (Execute to see output) */// :~
