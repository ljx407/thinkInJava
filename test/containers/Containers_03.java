package containers;

import static util.PrintUtil.println;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import util.Countries;

public class Containers_03 {

	@Test
	public void testCollectionMethod() {
		Collection<String> c = new ArrayList<String>();
		
		c.addAll(Countries.names(6));
		println("collectin : \n {}" , c);
		
		c.add("Seven");
		c.add("ten");
		
		println("collection.add() : \n {}" , c);
		println("collection.toArray() : \n {}",Arrays.toString(c.toArray()));
		println("collection.toArray(T[] t) : \n {}",Arrays.toString(c.toArray(new String[0])));
		
		List<String> cc = new ArrayList<String>(((List<String>)c).subList(3, 6));
		println("cc : {}" ,cc);
		
		println(c.containsAll(cc));
		cc.add("one");
		cc.add("two");
		cc.add("three");
		println("c : {}" ,c);
		println("cc : {}" ,cc);
		println(c.containsAll(cc));
		
		println(c.retainAll(cc));
		println(c);
		
		c.addAll(Countries.names(6));
		
		println(c.removeAll(cc));
		println(c);
		
		c.remove("BENIN");
		println(c);
		
		println("isEmpty : {}",c.isEmpty());
		c.clear();
		println("isEmpty after clear : {}",c.isEmpty());
		
		
	}
}
