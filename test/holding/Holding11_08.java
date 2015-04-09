package holding;

import java.util.LinkedList;
import java.util.Random;
import static util.PrintUtil.*;
import org.junit.Test;

public class Holding11_08 {
	@Test
	public void stackTest() {
		Stack<String> stack = new Stack<String>();
		for (String s : "hello world I am jasonliu".split(" ")) {
			stack.push(s);
		}
		while (!stack.isEmpty()) {
			print(stack.pop() + " ");
		}
	}

	@Test
	public void javaUtilStackTest() {
		java.util.Stack<String> stack = new java.util.Stack<String>();
		for (String s : "hello world I am jasonliu".split(" ")) {
			stack.push(s);
		}
		while (!stack.isEmpty()) {
			print(stack.pop() + " ");
		}
	}

	@Test
	public void test() {
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			print(random.nextInt(30) + " ");
		}
		println();
		Random random1 = new Random(30);
		for (int i = 0; i < 30; i++) {
			print(random1.nextInt(40) + " ");
		}
	}
}

class Stack<T> {
	LinkedList<T> linkedList = new LinkedList<T>();

	public void push(T t) {
		linkedList.add(t);
	}

	public T peek() {
		return linkedList.peekLast();
	}

	public T pop() {
		return linkedList.pollLast();
	}

	public boolean isEmpty() {
		return linkedList.isEmpty();
	}

	@Override
	public String toString() {
		return "Stack [linkedList=" + linkedList + "]";
	}
}