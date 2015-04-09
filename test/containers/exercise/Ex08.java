package containers.exercise;

import static util.PrintUtil.print;

import java.util.List;

import org.junit.Test;

import util.Countries;

public class Ex08 {

	@Test
	public void test() {
		int index = 0;
		while (index++ < 1) {
			System.out.println("ok");
		}
	}

	@Test
	public void testSList() {
		SList<String> sList = new SList<String>(Countries.names(6));
		SList<String>.SListIterator listIterator = sList.iterator();
		while(listIterator.hashNext()) {
			print("{} ",listIterator.next());
			if(listIterator.nextIndex() == 2) {
				listIterator.add("one");
			} else if(listIterator.nextIndex() == 4) {
				listIterator.remove();
			}
		}
		
		
	}
}

class SList<T> {

	private List<T> arrayList;

	public SList(List<T> arrayList) {
		this.arrayList = arrayList;
	}

	public SListIterator iterator() {
		return new SListIterator();
	}

	public class SListIterator {

		private int index = 0;

		public int nextIndex() {
			return index;
		}

		public boolean hashNext() {
			return index < arrayList.size();
		}

		public T next() {
			T t = arrayList.get(index);
			index++;
			return t;
		}

		public void add(T t) {
			arrayList.add(t);
		}

		public void remove() {
			arrayList.remove(index);
		}
	}
}
