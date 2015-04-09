package holding.exercise.standard;

import static util.PrintUtil.*;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("rawtypes")
public class Ex09Code {
	private ArrayList<Object> items = new ArrayList<Object>();

	public void add(Object x) {
		items.add(x);
	}

	public Iterator iterator() {
		return items.iterator();
	}

	public static void main(String[] args) {
		Ex09Code sequence = new Ex09Code();
		for (int i = 0; i < 10; i++)
			sequence.add(Integer.toString(i));
		Iterator it = sequence.iterator();
		while (it.hasNext()) {
			print(it.next() + " ");
		}
	}
}