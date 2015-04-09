package containers;

import static util.PrintUtil.print;
import static util.PrintUtil.println;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import util.CollectionData;
import util.CountingGenerator;
import util.Generator;
import util.MapData;
import util.Pair;
import util.RandomGenerator;

public class Containers_02 {

	@Test
	public void testCollections() {
		List<StringAddress> arrayList = new LinkedList<StringAddress>();
		arrayList.addAll(Collections.nCopies(4, new StringAddress("hello")));
		println(arrayList);

		Collections.fill(arrayList, new StringAddress("world"));
		println(arrayList);
	}

	@Test
	public void testGenerator() {
		List<StringAddress> list = CollectionData.list(new Generator<StringAddress>() {
			String[] ss = "hello jasonliu stay hungry stay foolish !".split(" ");
			private int index;

			@Override
			public StringAddress next() {
				if (index >= ss.length)
					index = 0;
				return new StringAddress(ss[index++]);
			}

		}, 3);

		println(list);
	}

	@Test
	public void testGoverment() {
		List<String> list = new ArrayList<String>(CollectionData.list(new GovermentGenerator(), 10));
		println(list);
	}

	@Test
	public void testCollectionDataGenerator() {
		Set<String> set = new HashSet<String>(CollectionData.list(new RandomGenerator.String(9), 4));
		println(set);

		List<Integer> list = new ArrayList<Integer>(CollectionData.list(new RandomGenerator.Integer(), 5));
		println(list);
	}

	@Test
	public void testLetterGenerator() {
		Map<Integer, String> map = MapData.map(new LettersGenerator(), 4);
		println(map);

		Map<Character, String> map1 = MapData.map(new CountingGenerator.Character(), new RandomGenerator.String(6), 10);
		println(map1);
		
		Map<Character,String> map2 = MapData.map(new CountingGenerator.Character(), "value" , 6);
		println(map2);
		
		Map<Integer,String> map3 = MapData.map(new LettersGenerator(), new RandomGenerator.String(6));
		println(map3);
		
		Map<Integer,String> map4 = MapData.map(new LettersGenerator(), "value");
		println(map4);
	}

	@Test
	public void test() {
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			print(random.nextInt(2) + " ");
		}
	}
	
	@Test
	public void test1() {
		Map<Integer,String> map = new HashMap<Integer,String>(){
			private static final long serialVersionUID = 1L;
			{
				this.put(1, "A");
				this.put(2, "B");
			}
		};
		println(map);
	}
	
	@Test
	public void test2() {
		Set<Integer> set = new HashSet<Integer>(){
			private static final long serialVersionUID = 1L;
			{
				this.add(1);
			}
		};
		println(set);
	}
	
	@Test
	public void test3() {
		int[] i = new int[]{1,2,3};
		println(Arrays.toString(i));
		
		int[][] ii = new int[][]{{1,2,3,4}};
		println(Arrays.toString(ii[0]));
	}	
}

class StringAddress {
	private String s;

	public StringAddress(String s) {
		this.s = s;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return super.toString() + " " + s;
	}
}

class GovermentGenerator implements Generator<String> {
	String[] str = "hello jasonliu stay hungry stay foolish !".split(" ");
	int index = 0;

	@Override
	public String next() {
		if (index >= str.length)
			index = 0;
		return str[index++];
	}
}

class LettersGenerator implements Generator<Pair<Integer, String>>, Iterable<Integer> {

	private int size = 9;
	private int number = 0;
	private Character letter = 'A';

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {

			@Override
			public boolean hasNext() {
				return number < size;
			}

			@Override
			public Integer next() {
				return number++;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

		};
	}

	@Override
	public Pair<Integer, String> next() {
		return new Pair<Integer, String>(number++, "" + letter++);
	}

}
