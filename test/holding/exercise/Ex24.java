package holding.exercise;

import static util.PrintUtil.println;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class Ex24 {
	@Test
	public void testLinkedHashMap() {
		Map<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
		linkedHashMap.put("ten", 10);
		linkedHashMap.put("nine", 9);
		linkedHashMap.put("eight", 8);
		linkedHashMap.put("seven", 7);
		linkedHashMap.put("six", 6);
		linkedHashMap.put("five", 5);
		linkedHashMap.put("four", 4);
		linkedHashMap.put("three", 3);
		linkedHashMap.put("two", 2);
		linkedHashMap.put("one", 1);
		linkedHashMap.put("zero", 0);
		
		println(linkedHashMap);
		
		Map<String, Integer> treeMap = new LinkedHashMap<String, Integer>();

		Set<String> keySet = new TreeSet<String>(linkedHashMap.keySet());
		for (String str : keySet) {
			treeMap.put(str, linkedHashMap.get(str));
		}
		
		linkedHashMap.clear();
		linkedHashMap.putAll(treeMap);
		println(linkedHashMap);
		
	}
}

