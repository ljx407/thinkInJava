package test;

public class Count {
	private int num = 0;

	public void count() {
		for (int i = 1; i <= 10; i++) {
			num += i;
		}
		System.out.println(Thread.currentThread().getName() + "-" + num);
	}
}
