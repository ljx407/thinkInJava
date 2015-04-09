package util;

import java.util.ArrayList;
import java.util.List;

public class CollectionData<T> extends ArrayList<T> {
	
	private static final long serialVersionUID = 1L;
	
	private CollectionData() {
	}
	
	private CollectionData(Generator<T> gen,int quantity) {
		for (int i = 0; i < quantity; i++) {
			add(gen.next());
		}
	}
	
	public static <T> List<T> list(Generator<T> gen,int num) {
		return new CollectionData<T>(gen,num);
	}
	
}
