package test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static util.PrintUtil.*;

public class TestExchange {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Exchanger<String> exchanger = new Exchanger<String>();
		executorService.execute(new ExchangeRunnable(exchanger, "A"));
		executorService.execute(new ExchangeRunnable(exchanger, "B"));
		try {
			executorService.shutdown();
			executorService.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ExchangeRunnable implements Runnable {

	Exchanger<String> exchanger = null;
	String data = null;

	public ExchangeRunnable(Exchanger<String> exchanger, String data) {
		this.exchanger = exchanger;
		this.data = data;
	}

	@Override
	public void run() {
		try {
			String changeData = exchanger.exchange(data);
			println("pre {},changed {}", data, changeData);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
