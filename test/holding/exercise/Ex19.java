package holding.exercise;

import static util.PrintUtil.println;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

import org.junit.Test;

public class Ex19 {
	
	@Test
	public void hashSetTest() {
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.add("S");
		hashSet.add("V");
		hashSet.add("C");
		hashSet.add("D");
		
		println(hashSet);
		
		TreeSet<String> treeSet = new TreeSet<String>(hashSet);
		println(treeSet);
		
		LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>(treeSet);
		println(linkedHashSet);
	}
}
