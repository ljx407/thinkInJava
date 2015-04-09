package holding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import org.junit.Test;
import static util.PrintUtil.*;

public class TestChange {
	public Collection<String> collectionInit(Collection<String> c) {
		c.add("rat");
		c.add("cat");
		c.add("dog");
		c.add("dog"); 
		return c;
	}

	public Map<String, String> mapInit(Map<String, String> map) {
		map.put("cat", "cat");
		map.put("rat", "rat");
		map.put("dog", "dog");
		map.put("rat", "rat1");
		return map;
	}

	@Test
	public void testCollection() {
		Collection<String> arrayList = collectionInit(new ArrayList<String>());
		println(arrayList);
		Collection<String> linkedList = collectionInit(new LinkedList<String>());
		println(linkedList);
		Collection<String> hashSet = collectionInit(new HashSet<String>());
		println(hashSet);
		Collection<String> treeSet = collectionInit(new TreeSet<String>());
		println(treeSet);
		Collection<String> linkedHashSet = collectionInit(new LinkedHashSet<String>());
		println(linkedHashSet);
	}

	@Test
	public void testMap() {
		Map<String, String> hashMap = mapInit(new HashMap<String, String>());
		println(hashMap);
		Map<String, String> treeMap = mapInit(new TreeMap<String, String>());
		println(treeMap);
		Map<String, String> linkedHashMap = mapInit(new LinkedHashMap<String, String>());
		println(linkedHashMap);
	}

	@Test
	public void treeSetTest() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		set.add(5);
		set.add(3);
		set.add(4);
		println(set);
		Iterator<Integer> iterator = set.descendingIterator();
		while (iterator.hasNext()) {
			println(iterator.next());
		}
	}
}