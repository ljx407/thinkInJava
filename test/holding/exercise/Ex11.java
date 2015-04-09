package holding.exercise;import java.util.ArrayList;import java.util.Collection;import java.util.HashSet;import java.util.Iterator;import java.util.LinkedHashSet;import java.util.LinkedList;import java.util.List;import org.junit.Test;import static util.PrintUtil.*; public class Ex11 {	@Test	public void testIterator() {		List<Rodent> list = RandomRodentGenerator.gen(5);		println(list);				ArrayList<Rodent> arrayList = new ArrayList<Rodent>(list);		display(arrayList);				LinkedList<Rodent> linkedList = new LinkedList<Rodent>(list);		display(linkedList);				HashSet<Rodent> hashSet = new HashSet<Rodent>(list);		display(hashSet);				LinkedHashSet<Rodent> linkedHashSet = new LinkedHashSet<Rodent>(list);		display(linkedHashSet);					}		public void display(Collection<Rodent> collection) {		Iterator<Rodent> iterator = collection.iterator();		while(iterator.hasNext())  {			Rodent rodent = iterator.next();			println(rodent);		}		println();	}	}