package containers;

import static util.PrintUtil.println;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class Containers_04 {
	
	public void test(String msg , List<String> l) {
		println("========={}=========",msg);
		Collection<String> cc = l;
		
		Collection<String> sublist = l.subList(1, 8);
		
		Collection<String> c = new ArrayList<String>();
		c.addAll(sublist);
		
		try {
			cc.add("one");
		} catch (Exception e) {
			println("cc.add():{}",e);
		}
		try {
			List<String> ll = (List<String>)cc ;
			ll.set(1, "three");
		} catch (Exception e) {
			println("ll.set():{}",e);
		}
		
		try {
			cc.removeAll(c);
		} catch (Exception e) {
			println("cc.removeAll():{}",e);
		}
		
		try {
			cc.retainAll(c);
		} catch (Exception e) {
			println("cc.retainAll():{}",e);
		}
		try {
			cc.clear();
		} catch (Exception e) {
			println("cc.clear():{}",e);
		}
		
		
	}
	
	@Test
	public void unsupport() {
		String[] a = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");
		List<String> asList = Arrays.asList(a);
		
		List<String> arrayList = new ArrayList<String>(asList);
		test("arrayList",arrayList);
		
		test("asList",asList);
		
		List<String> c = Collections.unmodifiableList(asList);
		test("unmodifiableCollection",c);
		
	}
	
}
