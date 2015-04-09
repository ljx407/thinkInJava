package holding.p3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class AsListReference {
	public static void main(String[] args) {
		List<A> list = Arrays.asList(new A1(1),new A2(2));
		System.out.println(list);
		
//		List<A> list1 = Arrays.asList(new A11(11),new A12(12));
//		System.out.println(list1);
		
		List<A> list2 = new ArrayList<A>();
		Collections.addAll(list2, new A11(11),new A12(12));
		System.out.println(list2);
		
		List<A> list3 = Arrays.<A>asList(new A11(11),new A12(12));
		
		TestGeneric<A> t1 = new TestGeneric<A>();
		t1.add(new A(1));
		t1.add(new A(2));
		t1.printT();
		
//		TestGeneric<A1> t2 = new TestGeneric<A1>();
//		t2.add(new A(1));
//		t2.add(new A(2));
//		t2.printT();
		
		TestGenericMethod t3 = new TestGenericMethod();
		t3.printT(new A1(2));
		
		
	}
}

class TestGeneric<T> {
	List<T> lists = new ArrayList<T>();
	
	public void add(T t) {
		lists.add(t);
	}
	
	public void printT() {
		if(!lists.isEmpty()) {
			for(T t : lists) {
				System.out.println(t);
			}
		}
	}
}

class TestGenericMethod {
	
	public <T> void printT(T t) {
		List<T> list = new ArrayList<T>();
		list.add(t);
		System.out.println(list);
	}
}

class A {
	int id ;
	
	public A(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "A [id=" + id + "]";
	}
}
class A1 extends A {

	public A1(int i) {
		super(i);
	}
	
}
class A2 extends A {
	public A2(int i) {
		super(i);
	}
}
class A11 extends A1 {
	public A11(int i) {
		super(i);
	}
}
class A12 extends A1 {
	public A12(int i) {
		super(i);
	}
}
class A21 extends A2 {
	public A21(int i) {
		super(i);
	}
}
class A22 extends A2 {
	public A22(int i) {
		super(i);
	}
}


