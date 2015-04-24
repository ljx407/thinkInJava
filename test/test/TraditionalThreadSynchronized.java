package test;

import java.util.concurrent.TimeUnit;

public class TraditionalThreadSynchronized {
	public static void main(String[] args) {
		final Outputter output = new Outputter();
		new Thread() {
			public void run() {
				output.output1("zhangsan");
			};
		}.start();
		new Thread() {
			public void run() {
				output.output1("lisi");
			};
		}.start();
	}
}

class Outputter {
	public synchronized void output(String name) {
		// TODO Ϊ�˱�֤��name���������һ��ԭ�Ӳ���������������name��ÿ���ַ�
		for (int i = 0; i < name.length(); i++) {
			System.out.print(name.charAt(i));
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void output1(String name) {
		synchronized(this) {
			// TODO Ϊ�˱�֤��name���������һ��ԭ�Ӳ���������������name��ÿ���ַ�
			for (int i = 0; i < name.length(); i++) {
				System.out.print(name.charAt(i));
				try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
