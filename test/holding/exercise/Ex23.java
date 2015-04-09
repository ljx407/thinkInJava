package holding.exercise;

import static util.PrintUtil.println;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class Ex23 {

	public Integer statisicTest(int n) {
		// long seed = new Date().getTime();
		Random random = new Random();

		Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			Integer randomNum = random.nextInt(20);
			Integer count = map.get(randomNum);
			map.put(randomNum, count == null ? 1 : ++count);
		}

		Integer max = Collections.max(map.values());
		Integer maxKey = 0;
		// Integer min = Collections.min(map.values());
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == max) {
				maxKey = entry.getKey();
//				println("max[key:{};value:{}]", maxKey, max);
			}
			// else if (entry.getValue() == min) {
			// println("min[key:{},value:{}]", entry.getKey(), min);
			// }
		}
		return maxKey;
	}

	@Test
	public void statisticTest1() {
		Map<Integer, Integer> linkedHashMap = new LinkedHashMap<Integer, Integer>();
		for (int i = 0; i < 2000; i++) {
			Integer maxKey = statisicTest(10000);
			Integer count = linkedHashMap.get(maxKey);
			linkedHashMap.put(maxKey, count == null ? 1 : ++count);
		}
		Integer max = Collections.max(linkedHashMap.values());
		Set<Map.Entry<Integer,Integer>> linkedHashSet = new LinkedHashSet<Map.Entry<Integer,Integer>>(linkedHashMap.entrySet());
		for(Map.Entry<Integer, Integer> entry : linkedHashSet) {
			if(entry.getValue() == max) {
				println("max key:{},value:{}",entry.getKey(),max);
			}
		}
	}
}
