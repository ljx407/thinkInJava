package containers.exercise;

import static util.PrintUtil.println;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

import util.CountingMapData;

public class Ex14 {

	@Test
	public void testProperties() {
		Properties properties = new Properties();
		properties.putAll(new CountingMapData(6));
		printKeyAndValue(properties);

		println("properties.containKey:{}", properties.containsKey(5));
		println("properties.containValue:{}", properties.containsValue("F0"));
		
		Iterator<Object> itr = properties.keySet().iterator();
		Object next = itr.next();
		properties.remove(next);
		printKeyAndValue(properties);
		
		println("properties.get:{}",properties.get(4));
		
		
	}

	private void printKeyAndValue(Map<Object, Object> map) {
		println("map name:{}", map.getClass().getSimpleName());
		println("map size:{}", map.size());
		println("map keySet:{}", map.keySet());
		println("map values:{}", map.values());
	}
	
	@Test
	public void test() {
		SortedSet<String> s = new TreeSet<String>();
		s.add("a");
		s.add("c");
		s.add("d");
		
		SortedSet<String> ss = s.subSet("a", "d"+"\0");
		println(s);
		println(ss);
	}
}
