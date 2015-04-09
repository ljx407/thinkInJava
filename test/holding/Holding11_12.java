package holding;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import static util.PrintUtil.*;

public class Holding11_12 {
	
	@Test
	public void interfaceVsIterator() {
		List<Pet> arrayList = Pet.arrayList(4);
		Set<Pet> linkedHashSet = new LinkedHashSet<Pet>(arrayList);
		
		List<String> linkedList = new LinkedList<String>(); 
		Collections.addAll(linkedList, "A","B","C","D");
		
		Map<String,Pet> linkedHashMap = new LinkedHashMap<String,Pet>();
		for (int i = 0; i < linkedList.size(); i++) {
			linkedHashMap.put(linkedList.get(i), arrayList.get(i));
		}	
		
		display(arrayList);
		display(linkedHashSet);
		display(linkedHashMap.keySet());
		display(linkedHashMap.values());
		
		println("");
		
		display(arrayList.iterator());
		display(linkedHashSet.iterator());
		display(linkedHashMap.keySet().iterator());
		display(linkedHashMap.values().iterator());
	}
	
	@Test
	public void extendsAbstractCollection() {
		CollectionSequence cs = new CollectionSequence();
		display(cs);
	}
	
	@Test
	public void addIterator() {
		NoCollectionSequence ncs = new NoCollectionSequence();
		display(ncs.iterator());
	}
	
	private <T> void display(Collection<T> collection) {
		for (T t : collection) {
			print(t + " ");
		}
		println("");
	}
	
	private <T> void display(Iterator<T> iterator) {
		while(iterator.hasNext()) {
			print(iterator.next() + " ");
		}
		println("");
	}
}

class CollectionSequence extends AbstractCollection<Pet> {
	
	Pet[] pets = new Pet[8];
	
	public CollectionSequence() {
		pets = Pet.arrayList(8).toArray(new Pet[8]);
	}

	@Override
	public Iterator<Pet> iterator() {
		return new Iterator<Pet>(){
			int index = 0 ;
			@Override
			public boolean hasNext() {
				return index < pets.length;
			}

			@Override
			public Pet next() {
				return pets[index++];
			}

			@Override
			public void remove() {
				throw new RuntimeException();
			}
		};
	}

	@Override
	public int size() {
		return pets.length;
	}
	
}

class PetSequence {
	protected Pet[] pets = Pet.arrayList(8).toArray(new Pet[1]);
}

class NoCollectionSequence extends PetSequence {
	private Iterator<Pet> iterator = new Iterator<Pet>() {
		int index = 0;
		@Override
		public boolean hasNext() {
			return index < pets.length;
		}

		@Override
		public Pet next() {
			return pets[index++];
		}

		@Override
		public void remove() {
			throw new RuntimeException();
		}
	};
	
	public Iterator<Pet> iterator() {
		return iterator;
	}
}

