package test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest1 {
	static class MyTimerTask1 extends TimerTask {
		public void run() {
			System.out.println("��ը������");
			new Timer().schedule(new MyTimerTask2(), 2000);
		}
	}

	static class MyTimerTask2 extends TimerTask {
		public void run() {
			System.out.println("��ը������");
			new Timer().schedule(new MyTimerTask1(), 3000);
		}
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new MyTimerTask2(), 2000);
		while (true) {
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}