package concurrency.exercise;

import static util.PrintUtil.println;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Ex14 {
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask(){
			@Override
			public void run() {
				println();
				System.err.println("aborted!");
				System.exit(0);
			}
		}, 100);
		MyTimerTask myTimerTask = new MyTimerTask();
		for (int i = 0; i < 2; i++) {
			Timer timer = new Timer();
			timer.schedule(myTimerTask, 100);
		}
	}
}

class MyTimerTask extends TimerTask {
	
	AtomicInteger atomicInteger = new AtomicInteger(0);
	
	public MyTimerTask() {
		super();
	}
	
	public Integer get() {
		atomicInteger.getAndIncrement();
		return atomicInteger.get(); 
	}

	@Override
	public void run() {
		while(true) {
			println("{}:{}",Thread.currentThread().getName(),get());
		}
	}
	
}
