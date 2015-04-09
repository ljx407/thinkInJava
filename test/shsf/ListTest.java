package shsf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class ListTest {
	List<String> list1, list2 ,list3;

	@Before
	public void before() {
		list1 = new ArrayList<String>();
		Collections.addAll(list1, "111111", "222222", "333333" ,"222222");

		list2 = new ArrayList<String>();
		Collections.addAll(list2, "333333", "444444", "555555");
		
	}

	@Test
	public void addAllTest() {
		list1.addAll(list2);
		System.out.println(list1);
	}

	@Test
	public void retainAllTest() {
		list1.retainAll(list2);
		System.out.println(list1);
	}

	@Test
	public void removeAllTest() {
		list1.removeAll(list2);
		list1.addAll(list2);
		System.out.println(list1);
	}
	
	@Test
	public void copyTest() {
		list3 = new ArrayList<String>(list1);
		System.out.println(list3);
	}
	
	@Test
	public void removeDuplicate() {
		Set<String> set = new LinkedHashSet<String>(list1);
		System.out.println(set);
	}
	
	@Test
	public void countDuplicate() {
		Map<String,Integer> map = new LinkedHashMap<String,Integer>();
		for(String s : list1) {
			if(map.containsKey(s)) {
				Integer count = map.get(s);
				map.put(s, ++count);
			} else {
				map.put(s, 1);
			}
		}
		System.out.println(map);
	}
}
