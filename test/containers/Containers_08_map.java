package containers;

import static util.PrintUtil.println;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import util.CountingMapData;

public class Containers_08_map {

	@Test
	public void test() {
		AssociationArray<Integer, String> aa = new AssociationArray<Integer, String>(6);
		for (int i = 0; i < 6; i++) {
			aa.put(i, "" + i);
		}
		println(aa);
	}

	@Test
	public void testBaseMap() {
		baseOperationMap(new HashMap<Integer, String>());
		baseOperationMap(new TreeMap<Integer, String>());
		baseOperationMap(new LinkedHashMap<Integer, String>());
		baseOperationMap(new IdentityHashMap<Integer, String>());
		baseOperationMap(new ConcurrentHashMap<Integer, String>());
		baseOperationMap(new WeakHashMap<Integer, String>());
	}

	@Test
	public void testSortedMapDemo() {
		SortedMap<Integer, String> sortedMap = new TreeMap<Integer, String>();
		sortedMap.putAll(new CountingMapData(10));
		println(sortedMap);
		Integer firstKey = sortedMap.firstKey();
		println(firstKey);
		Integer lastKey = sortedMap.lastKey();
		println(lastKey);
		Iterator<Integer> itr = sortedMap.keySet().iterator();
		for (int i = 0; i < sortedMap.keySet().size(); i++) {
			Integer next = itr.next();
			if (i == 3) {
				firstKey = next;
			} else if (i == 6) {
				lastKey = next;
			}
		}
		println(firstKey);
		println(lastKey);

		println(sortedMap.subMap(firstKey, lastKey));
		println(sortedMap.headMap(firstKey));
		println(sortedMap.tailMap(lastKey));
	}

	@Test
	public void testLinkedHashMap() {
		LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>(new CountingMapData(10));
		println(linkedHashMap);
		linkedHashMap = new LinkedHashMap<Integer, String>(16, 0.75f, true);
		linkedHashMap.putAll(new CountingMapData(10));
		for (int i = 0; i < 6; i++) {
			linkedHashMap.get(i);
		}
		println(linkedHashMap);
		linkedHashMap.get(0);
		linkedHashMap.get(4);
		println(linkedHashMap);
	}
	
	@Test
	public void testMap() {
		springDetect(GroundHog.class);
		springDetect(GroundHog2.class);
	}

	public <T extends GroundHog> void springDetect(Class<T> type) {
		try {
			Constructor<T> constructor = type.getConstructor(int.class);
			Map<GroundHog, prediction> map = new HashMap<GroundHog, prediction>();
			for (int i = 0; i < 9; i++) {
				T newInstance = constructor.newInstance(i);
				map.put(newInstance, new prediction());
			}
			println("map:{}",map);
			GroundHog newInstance = constructor.newInstance(3);
			if(map.containsKey(newInstance)) {
				println("prediction:{}",map.get(newInstance));
			} else {
				println("get null !");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	private void printKeyAndValues(Map<Integer, String> map) {
		println("Size = {}", map.size());
		println("keySet : {}", map.keySet());
		println("values : {}", map.values());
	}

	private void baseOperationMap(Map<Integer, String> map) {
		println("name : {}", map.getClass().getSimpleName());
		map.putAll(new CountingMapData(10));
		map.putAll(new CountingMapData(10)); // ≤ªø…÷ÿ∏¥
		println(map);
		printKeyAndValues(map);

		println("map.containsKey():{}", map.containsKey(9));
		println("map.get():{}", map.get(9));
		println("map.containsValue():{}", map.containsValue("A0"));

		Iterator<Integer> itr = map.keySet().iterator();
		Integer next = itr.next();
		map.remove(next);

		printKeyAndValues(map);

		map.clear();
		println("map.isEmpty:{}", map.isEmpty());

		map.putAll(new CountingMapData(10));
		map.keySet().removeAll(map.keySet());

		println("map.isEmpty:{}", map.isEmpty());

	}

	public class AssociationArray<K, V> {
		private Object[][] values;
		private int index;

		public AssociationArray(int capicity) {
			values = new Object[capicity][2];
		}

		public void put(K k, V v) {
			if (index >= values.length) {
				throw new ArrayIndexOutOfBoundsException();
			}
			if (get(k) == null) {
				values[index++] = new Object[] { k, v };
			} else {
				for (int i = 0; i < index; i++) {
					if (k.equals(values[i][0])) {
						values[i][1] = v;
					}
				}
			}
		}

		@SuppressWarnings("unchecked")
		public V get(K k) {
			for (int i = 0; i < index; i++) {
				if (k.equals(values[i][0])) {
					return (V) values[i][1];
				}
			}
			return null;
		}

		@Override
		public String toString() {
			StringBuffer result = new StringBuffer("[");
			for (int i = 0; i < index; i++) {
				result.append(" " + values[i][0] + ":" + values[i][1]);
			}
			result.append("]");
			return result.toString();
		}
	}
}

class GroundHog {
	public int number;
	
	public GroundHog(int number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return "GroundHog [number=" + number + "]";
	}
}

class GroundHog2 extends GroundHog {
	public GroundHog2(int number) {
		super(number);
	}
	
	@Override
	public int hashCode() {
		return super.number;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof GroundHog2 && ((GroundHog2)obj).number == this.number;
	}

	@Override
	public String toString() {
		return "GroundHog [number=" + number + "]";
	}
}

class prediction {
	private Random random = new Random(47);
	private boolean shawow = random.nextDouble() > 0.5;

	@Override
	public String toString() {
		return "prediction [random=" + random + ", shawow=" + shawow + "]";
	}
}
