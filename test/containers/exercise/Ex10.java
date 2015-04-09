package containers.exercise;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

import util.RandomGenerator;
import static util.PrintUtil.*;

public class Ex10 {
	@Test
	public void test() {
		MySortedSet<String> m = new MySortedSet<String>();
		String s = null;
		for (int i = 0; i < 8; i++) {
			String next = new RandomGenerator.String(2).next();
			m.add(next);
			m.add(next);
			if(i == 4) {
				s = next ;
			}
 		}
		println(m);
		println("first:{}",m.first());
		println("last:{}",m.last());
		println(m.headSet(s));
		println(m.tailSet(s));
	}
}

class MySortedSet<T> implements SortedSet<T> {
	
	private List<T> list = new LinkedList<T>();
	private Comparator<? super T> comparator ;
	
	public MySortedSet() {
		super();
	}

	public MySortedSet(Comparator<? super T> comparator) {
		this.comparator = comparator;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
 		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	@Override
	public boolean add(T e) {
		return contains(e) ? false : list.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return list.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public Comparator<? super T> comparator() {
		return comparator;
	}

	@Override
	public SortedSet<T> subSet(T fromElement, T toElement) {
		return new TreeSet<T>(list.subList(list.indexOf(fromElement), list.indexOf(toElement)));
	}

	@Override
	public SortedSet<T> headSet(T toElement) {
		return new TreeSet<T>(list.subList(0, list.indexOf(toElement)));
	}

	@Override
	public SortedSet<T> tailSet(T fromElement) {
		return new TreeSet<T>(list.subList(list.indexOf(fromElement), list.size()));
	}

	@Override
	public T first() {
		return list.get(0);
	}

	@Override
	public T last() {
		return list.get(list.size()-1);
	}

	@Override
	public String toString() {
		return list.toString();
	}
	
	
}
