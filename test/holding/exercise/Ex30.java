package holding.exercise;

import static util.PrintUtil.print;
import static util.PrintUtil.println;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class Ex30 {

	@Test
	public void testExtendCollection() {
		CollectionSequence cs = new CollectionSequence();
		cs.add(new Person(5, "E"));

		display(cs);
		display(cs.iterator());

		Person d = new Person(4, "D");
		println(cs.contains(d));
	}

	private <T> void display(Collection<T> collection) {
		for (T t : collection) {
			print(t + " ");
		}
		println("");
	}

	private <T> void display(Iterator<T> iterator) {
		while (iterator.hasNext()) {
			print(iterator.next() + " ");
		}
		println("");
	}
}

class Person {
	private int id;
	private String name;

	public Person(String name) {
		this(0, name);
	}

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Person))
			return false;
		return ((Person) obj).getId() == this.getId();
	}

}

class CollectionSequence implements Collection<Person> {

	private Person[] persons = null;
	List<Person> list = null;

	public CollectionSequence() {
		list = new ArrayList<Person>();
		Collections.addAll(list, new Person(1, "A"), new Person(2, "B"), new Person(4, "D"), new Person(3, "C"));
		persons = list.toArray(new Person[1]);
	}

	public CollectionSequence(Collection<Person> collection) {
		persons = collection.toArray(new Person[1]);
	}

	public Person[] getPersons() {
		return persons;
	}

	public void setPersons(Person[] persons) {
		this.persons = persons;
	}

	@Override
	public int size() {
		return persons.length;
	}

	@Override
	public boolean isEmpty() {
		return persons.length > 0;
	}

	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < persons.length; i++) {
			if (persons[i].equals(o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<Person> iterator() {
		return new Iterator<Person>() {
			int index = 0;

			@Override
			public boolean hasNext() {
				return index < persons.length;
			}

			@Override
			public Person next() {
				return persons[index++];
			}

			@Override
			public void remove() {
				throw new RuntimeException();
			}

		};
	}

	@Override
	public Object[] toArray() {
		return persons;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean add(Person e) {
		boolean flag = list.add(e);
		setPersons(list.toArray(new Person[1]));
		return flag;
	}

	@Override
	public boolean remove(Object o) {
		boolean flag = list.remove(o);
		setPersons(list.toArray(new Person[1]));
		return flag;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Person> c) {
		boolean flag = list.addAll(c);
		setPersons(list.toArray(new Person[1]));
		return flag;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean flag = list.removeAll(c);
		setPersons(list.toArray(new Person[1]));
		return flag;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean flag = list.retainAll(c);
		setPersons(list.toArray(new Person[1]));
		return flag;
	}

	@Override
	public void clear() {
		persons = new Person[0];
	}

}
