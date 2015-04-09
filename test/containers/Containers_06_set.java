package containers;

import static util.PrintUtil.println;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

public class Containers_06_set {

	public <T> void fill(Set<T> set, Class<T> t) {
		for (int i = 0; i < 10; i++) {
			try {
				set.add(t.getConstructor(int.class).newInstance(i));
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	public <T> void test(Set<T> set, Class<T> t) {
		fill(set, t);
		fill(set, t);
		fill(set, t);
		println(set);
	}

	@Test
	public void testSet() {
		// test(new HashSet<SetType>(),SetType.class);
		test(new HashSet<HashType>(), HashType.class);
		test(new LinkedHashSet<HashType>(), HashType.class);
		test(new TreeSet<TreeType>(), TreeType.class);

		test(new HashSet<SetType>(), SetType.class);
		test(new HashSet<TreeType>(), TreeType.class);

		test(new LinkedHashSet<SetType>(), SetType.class);
		test(new LinkedHashSet<TreeType>(), TreeType.class);

		try {
			test(new TreeSet<SetType>(), SetType.class);
		} catch (Exception e) {
			println(e);
		}
		try {
			test(new TreeSet<HashType>(), HashType.class);
		} catch (Exception e) {
			println(e);
		}

	}

	@Test
	public void test2() {
		Set<SetType> set = new HashSet<SetType>();
		SetType s1 = new SetType(1);
		println("s1.hashCode() {}", s1.hashCode());
		SetType s3 = new SetType(1);
		println("s3.hashCode() {}", s3.hashCode());
		SetType s2 = new SetType(2);
		println("s2.hashCode() {}", s2.hashCode());
		set.add(s1);
		set.add(s2);
		set.add(s3);

		println(set);
		println(s1.equals(s3));
	}

	@Test
	public void testSortedSet() {
		SortedSet<String> sortedSet = new TreeSet<String>();
		Collections.addAll(sortedSet, "one three two eight five four seven".split(" "));
		println("orignal:{}", sortedSet);

		String first = sortedSet.first();
		println("first:{}", first);
		String last = sortedSet.last();
		println("last:{}", last);

		Iterator<String> itr = sortedSet.iterator();

		for (int i = 0; i < sortedSet.size(); i++) {
			if (i == 2) {
				first = itr.next();
			} else if (i == 5) {
				last = itr.next();
			} else {
				itr.next();
			}
		}

		println("first:{}", first);
		println("last:{}", last);

		SortedSet<String> subset = sortedSet.subSet(first, last);
		println("subset:{}", subset);
		
		println("headSet:{}",sortedSet.headSet(first));
		println("tailSet:{}",sortedSet.tailSet(last));

	}

}

class SetType {
	int i;

	public SetType(int i) {
		this.i = i;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof SetType) && (((SetType) obj).i == this.i);
	}

	@Override
	public String toString() {
		return String.valueOf(i);
	}

}

class HashType extends SetType {

	public HashType(int i) {
		super(i);
	}

	@Override
	public int hashCode() {
		return i;
	}
}

class TreeType extends SetType implements Comparable<TreeType> {

	public TreeType(int i) {
		super(i);
	}

	@Override
	public int compareTo(TreeType o) {
		return o.i < this.i ? -1 : (o.i == this.i ? 0 : 1);
	}

}
