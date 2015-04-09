package util;

import java.util.LinkedHashMap;

public class MapData<K,V> extends LinkedHashMap<K,V> {
	
	private static final long serialVersionUID = 1L;

	private MapData(Generator<Pair<K,V>> gen , int quantity) {
		for (int i = 0; i < quantity; i++) {
			Pair<K,V> pair = gen.next();
			put(pair.k,pair.v);
		}
	}
	private MapData(Generator<K> k , Generator<V> v , int quantity) {
		for (int i = 0; i < quantity; i++) {
			put(k.next(),v.next());
		}
	}
	private MapData(Generator<K> k , V v ,int quantity) {
		for (int i = 0; i < quantity; i++) {
			put(k.next(),v);
		}
	}
	private MapData(Iterable<K> k , Generator<V> v ) {
		for(K key : k) {
			put(key,v.next());
		}
	}
	private MapData(Iterable<K> k , V v) {
		for(K key : k) {
			put(key , v);
		}
	}
	
	public static <K,V> MapData<K,V> map(Generator<Pair<K,V>> gen , int quantity) {
		return new MapData<K,V>(gen,quantity);
	}
	
	public static <K,V> MapData<K,V> map(Generator<K> k , Generator<V> v ,int quantity) {
		return new MapData<K,V>(k,v,quantity);
	}
	
	public static <K,V> MapData<K,V> map(Generator<K> k , V v ,int quantity) {
		return new MapData<K,V>(k,v,quantity);
	}
	
	public static <K,V> MapData<K,V> map(Iterable<K> k,Generator<V> v) {
		return new MapData<K,V>(k,v);
	}
	
	public static <K,V> MapData<K,V> map(Iterable<K> k , V v) {
		return new MapData<K,V>(k,v);
	}
}
