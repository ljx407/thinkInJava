package holding.p2;

//innerclasses/Sequence2.java
//TIJ4 Chapter Innerclasses, Exercise 2, page 350
/* Create a class that holds a String, and has a toString() method that
 * displays this String. Add several instances of your new class to a 
 * Sequence ojbect, then display them.
 */

class Word1 {
	private String word;

	public Word1(String s) {
		word = s;
	}

	public String toString() {
		return word;
	}
}

interface Selector {
	boolean end();

	Object current();

	void next();
}

public class Sequence {
	private Object[] items;
	private int next = 0;

	public Sequence(int size) {
		items = new Object[size];
	}

	public void add(Object x) {
		if (next < items.length)
			items[next++] = x;
	}

	private class SequenceSelector implements Selector {
		private int i = 0;

		public boolean end() {
			return i == items.length;
		}

		public Object current() {
			return items[i];
		}

		public void next() {
			if (i < items.length)
				i++;
		}
	}

	public Selector selector() {
		return new SequenceSelector();
	}

	public static void main(String[] args) {
		Sequence sequence = new Sequence(10);
		for (int i = 0; i < 10; i++)
			sequence.add(new Word1(Integer.toString(i)));
		Selector selector = sequence.selector();
		while (!selector.end()) {
			System.out.print(selector.current() + " ");
			selector.next();
		}
		Word1 w1 = new Word1("Peace");
		Word1 w2 = new Word1("Love");
		Word1 w3 = new Word1("Easter");
		Sequence message = new Sequence(3);
		message.add(w1);
		message.add(w2);
		message.add(w3);
		Selector sel = message.selector();
		while (!sel.end()) {
			System.out.print(sel.current() + " ");
			sel.next();
		}

	}
}
