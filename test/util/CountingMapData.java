package util;

import static util.PrintUtil.println;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class CountingMapData extends AbstractMap<Integer, String> {

	private int size;

	private static final String[] chars = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");

	public CountingMapData(int size) {
		this.size = size < 0 ? 0 : size;
	}

	@Override
	public Set<Map.Entry<Integer, String>> entrySet() {
		Set<Map.Entry<Integer, String>> linkedHashSet = new LinkedHashSet<Map.Entry<Integer, String>>();

		for (int i = 0; i < size; i++) {
			linkedHashSet.add(new Entry(i));
		}

		return linkedHashSet;
	}

	private static class Entry implements Map.Entry<Integer, String> {

		private int index ;

		public Entry(int index) {
			this.index = index;
		}

		@Override
		public Integer getKey() {
			return index;
		}

		@Override
		public String getValue() {
			return chars[index % chars.length] + Integer.toString(index / chars.length);
		}

		@Override
		public String setValue(String value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int hashCode() {
			return Integer.valueOf(index).hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			return Integer.valueOf(index).equals(obj);
		}

	}
	
	@SuppressWarnings("unused")
	private class EntrySet extends AbstractSet<Map.Entry<Integer, String>> {
		

		@Override
		public Iterator<Map.Entry<Integer, String>> iterator() {
			return new Itr();
		}

		@Override
		public int size() {
			return size;
		}

		class Itr implements Iterator<Map.Entry<Integer, String>> {
			
			private Entry entry = new Entry(-1);

			@Override
			public boolean hasNext() {
				return entry.index < size-1;
			}

			@Override
			public Map.Entry<Integer, String> next() {
				entry.index ++ ;
				return entry;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		println(new CountingMapData(60));
	}

}
