package test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	static class MyTimerTask1 extends TimerTask {
		public void run() {
			System.out.println("爆炸！！！");
		}
	}

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new MyTimerTask1(), 2000);// 两秒后启动任务
//		timer.cancel();
	}
}