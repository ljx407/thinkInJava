package holding.exercise;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import static util.PrintUtil.*;
import org.junit.Test;

public class Ex15 {
	@Test
	public void testStack() throws IOException {
		String str = "+U+n+c---+c+r+t---+a-+i-+i-+n+t+y---+ -+r+u--+l+c+s---";
		StringReader sr = new StringReader(str);
		Stack<Character> stack = new Stack<Character>();
		int i = -1;
		while ((i = sr.read()) != -1) {
			switch (i) {
			case 43:
				char c = (char) sr.read();
				stack.push(c);
				break;
			case 45:
				print(stack.pop() + " ");
				break;
			}
		}
	}

	@Test
	public void test() {
		char c = "-".toCharArray()[0];
		println((int) c);
	}
}

class Stack<T> {
	private LinkedList<T> linkedList = new LinkedList<T>();

	public void push(T t) {
		linkedList.offerFirst(t);
	}

	public T pop() {
		return linkedList.remove();
	}

	public T peek() {
		return linkedList.element();
	}

	public boolean isEmpty() {
		return linkedList.isEmpty();
	}
}