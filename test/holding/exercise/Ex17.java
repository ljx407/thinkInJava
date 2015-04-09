package holding.exercise;

import static util.PrintUtil.println;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class Ex17 {
	
	Map<String,Gerbil> hashMap ;
	
	@Before
	public void before() {
		hashMap = new HashMap<String,Gerbil>();
		
		hashMap.put("A", new Gerbil(1));
		hashMap.put("B", new Gerbil(2));
		hashMap.put("C", new Gerbil(3));
		hashMap.put("D", new Gerbil(4));
	}
	
	@Test
	public void hashMapTest() {
		for(String str : hashMap.keySet()) {
			println("key:{},value:{}",str,hashMap.get(str).hop());
		}
	}
	
	@Test
	public void treeMapTest() {
		Map<String,Gerbil> treeMap = new TreeMap<String,Gerbil>(hashMap);
		Set<String> set = treeMap.keySet();
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()) {
			String key = iterator.next(); 
			println("key:{},value:{}",key,treeMap.get(key).hop());
		}
	}
	
	@Test
	public void linkedHashMapTest() {
		Map<String,Gerbil> linkedHashMap = new LinkedHashMap<String,Gerbil>(hashMap);
		for(Map.Entry<String,Gerbil> entry : linkedHashMap.entrySet()) {
			println("key:{},value:{}",entry.getKey() , entry.getValue().hop());
		}
	}
	
	
}
