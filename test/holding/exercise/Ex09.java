package holding.exercise;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;
import static util.PrintUtil.*;

public class Ex09 {
	@SuppressWarnings("unchecked")
	@Test
	public void testIterator() {
		Sequence sequence = new Sequence(10);
		for (int i = 0; i < 10; i++) {
			sequence.add(new Word1(Integer.toString(i)));
		}
		Iterator<Object> iterator = sequence.iterator();
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			print(obj + " ");
		}
		println();
		Word1 w1 = new Word1("Peace");
		Word1 w2 = new Word1("Love");
		Word1 w3 = new Word1("Easter");
		Sequence message = new Sequence(3);
		message.add(w1);
		message.add(w2);
		message.add(w3);
		iterator = message.iterator();
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			print(obj + " ");
		}
	}
}/* * Create a class that holds a String, and has a toString() method that displays this String. Add several instances of your new class to a Sequence ojbect, then display them. */

class Word1 {
	private String word;

	public Word1(String s) {
		word = s;
	}

	public String toString() {
		return word;
	}
}

class Sequence {
	private Object[] items;
	private int next = 0;

	public Sequence(int size) {
		items = new Object[size];
	}

	public void add(Object x) {
		if (next < items.length)
			items[next++] = x;
	}

	@SuppressWarnings("rawtypes")
	public Iterator iterator() {
		List<Object> objs = Arrays.asList(items);
		return objs.iterator();
	}
}