package holding.exercise;

import static util.PrintUtil.print;
import static util.PrintUtil.println;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

public class Ex18 {
	
	HashMap<String,String> hashMap ;
	
	@Before
	public void before() {
		hashMap = new HashMap<String,String>();
		hashMap.put("A", "AA");
		hashMap.put("B", "BB");
		hashMap.put("C", "CC");
		hashMap.put("D", "DD");
	}
	
	@Test
	public void testHashMap() {
		println(hashMap);
		
		for(String str : hashMap.keySet()) {
			println("key:{},value:{}",str,hashMap.get(str));
			print(" hashCode:{}",hashMap.hashCode());
		}
	}
	
	@Test
	public void testHashMap1() {
		println(hashMap);
		Set<String> keySet = hashMap.keySet();
		TreeSet<String> treeSet = new TreeSet<String>(keySet);
		println(treeSet);
		LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<String,String>();
		for(String str : treeSet) {
			linkedHashMap.put(str, hashMap.get(str));
		}
		println(linkedHashMap);
	}
	
	@Test
	public void testHashMap2() {
		println(hashMap);
		TreeMap<String ,String> treeMap = new TreeMap<String,String>(hashMap);
		println(treeMap);
		LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<String,String>(treeMap);
		println(linkedHashMap);
	}
	
}
