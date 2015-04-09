package holding;

import static util.PrintUtil.print;
import static util.PrintUtil.println;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

public class Holding11_13 {

	@Test
	public void testIterable() {
		List<String> arrayList = Arrays.asList("hello jasonliu stay hungry stay foolish".split(" "));
		printIterable(arrayList);

	}

	@Test
	public void testIterable1() {
		for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
			println("key:{},value:{}", entry.getKey(), entry.getValue());
		}
	}

	@Test
	public void testIterable2() {
		IterableClass iterableClass = new IterableClass();
		for (String str : iterableClass) {
			print(str + " ");
		}
	}

	@Test
	public void testArray() {
		String[] str = "hello jasonliu stay hungry stay foolish !".split(" ");
		for (String s : str) {
			print(s + " ");
		}
		// printIterable(str);
		println("");
		printIterable(Arrays.asList(str));
	}

	@Test
	public void testAdapter() {
		MutilIterableClass adapterMethodIdiom = new MutilIterableClass();
		for (String s : adapterMethodIdiom) {
			print(s + " ");
		}
		println();
		for (String ss : adapterMethodIdiom.reversedIterable()) {
			print(ss + " ");
		}
		println();
		for(String sss : adapterMethodIdiom.randomized()) {
			print(sss + " ");
		}
	}
	
	@Test
	public void testReversibleArrayList() {
		ReversibleArrayList<String> arrayList = new ReversibleArrayList<String>();
		Collections.addAll(arrayList, "hello jasonliu stay hungry stay foolish !".split(" "));
		for(String s : arrayList) {
			print(s + " ");
		}
		println();
		for(String ss : arrayList.reversedIterable()) {
			print(ss + " ");
		}
	}
	
	@Test
	public void midifyArrayAsList() {
		Integer[] init = {1,4,2,6,3,5,7,9};
		List<Integer> list = Arrays.asList(init);
		println("list : {}",list);
		List<Integer> arrayList = new ArrayList<Integer>(list);
		println("arrayList:{}",arrayList);
		Collections.shuffle(arrayList, new Random(693));
		println("shuffle arraylist:{}",arrayList);
		println("list : {}",list);
		println("array : {}" ,Arrays.toString(init));
		
		Collections.shuffle(list, new Random(693));
		println("shuffle list : {}" , list);
		println("array : {}" ,Arrays.toString(init));
	}

	private void printIterable(Iterable<String> iterable) {
		for (String str : iterable) {
			print(str + " ");
		}
	}

	@Test
	public void test() {
		// String str = "value:{}";
		String value = "$P$G";
		value.replaceAll("$", "\\\\\\$");
		println(value);
		// String temp = str.replaceAll("\\{\\}", value);//"\\$P\\$G"
		// println(temp);

		// str = "$P$G";
		// temp = str.replaceAll("$", "\\$");
		// println(temp);

		// Pattern pattern = Pattern.compile("\\{\\}");
		// Matcher matcher = pattern.matcher(str);
		//
		// String temp = matcher.replaceAll("\\$P\\$G");
		// println(temp);
	}

}

class IterableClass implements Iterable<String> {
	protected String[] words = "hello jasonliu stay hungry stay foolish !".split(" ");

	public String[] getWords() {
		return words;
	}

	public void setWords(String[] words) {
		this.words = words;
	}

	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {

			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < words.length;
			}

			@Override
			public String next() {
				return words[index++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

}

class MutilIterableClass extends IterableClass {

	public Iterable<String> reversedIterable() {
		return new Iterable<String>() {
			@Override
			public Iterator<String> iterator() {
				return new Iterator<String>() {
					private int index = words.length - 1;

					@Override
					public boolean hasNext() {
						return index > -1;
					}

					@Override
					public String next() {
						return words[index--];
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}

	public Iterable<String> randomized() {
		return new Iterable<String>() {

			@Override
			public Iterator<String> iterator() {
				List<String> wordsList = Arrays.asList(words);
				println("wordList : {}",wordsList);
				List<String> arrayList = new ArrayList<String>(wordsList);
				println("arrayList : {}",arrayList);
				Collections.shuffle(arrayList, new Random(369));
				println("shuffled arrayList : {}" , arrayList);
				println("wordList : {}",wordsList);
				return arrayList.iterator();
			}
		};
	}

}

class ReversibleArrayList<T> extends ArrayList<T> {
	
	private static final long serialVersionUID = 1L;

	public Iterable<T> reversedIterable() {
		return new Iterable<T>() {

			@Override
			public Iterator<T> iterator() {
				return new Iterator<T>(){
					
					int length = size() - 1;
					
					@Override
					public boolean hasNext() {
						return length > -1;
					}

					@Override
					public T next() {
						return get(length--);
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
					
				};
			}
			
		};
	}
}
