package containers.exercise;

import static util.PrintUtil.println;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class Ex12 {
	@Test
	public void testAssociateArray() {
		statistic("hashMap", new HashMap<Integer, String>());
		statistic("treeMap", new TreeMap<Integer, String>());
		statistic("linkedHashMap", new LinkedHashMap<Integer, String>());
	}

	private long fillMap(Map<Integer, String> map) {
		long currentTimeMillis = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			map.put(i, "" + i);
		}
		long endTimeMillis = System.currentTimeMillis();
		return endTimeMillis-currentTimeMillis;
	}
	
	private void statistic(String des , Map<Integer, String> m) {
		Map<Long,Integer> map = new HashMap<Long,Integer>();
		for(int i=0; i<1;i++) {
			long sec = fillMap(m);
			Integer count = map.get(sec);
			map.put(sec, count == null ? 1 : ++count);
		}
		Integer max = Collections.max(map.values());
		Map<Long,Integer> resultMap = new HashMap<Long,Integer>();
		for(Map.Entry<Long, Integer> entry : map.entrySet()) {
			if(entry.getValue() == max) {
				resultMap.put(entry.getKey(), entry.getValue());
			}
		}
		println("{}:{}",des,resultMap);
	}
}
