package holding.exercise;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import util.TextFile;

import static util.PrintUtil.*;

public class Ex20 {
	
	@Test
	public void mapTest() {
		Set<String> treeSet = new TreeSet<String>(new TextFile("holding\\SetOperation.java","\\w+"));
		
		Set<Character> vowels = new TreeSet<Character>();
		Collections.addAll(vowels, 'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u');
		
		Map<Character,Integer> hashMap = new HashMap<Character,Integer>();
		for(String str : treeSet) {
			for(char c : str.toCharArray()) {
				if(vowels.contains(c)) {
					Integer count = hashMap.get(c);
					hashMap.put(c, count == null ? 1 : ++count);
				}
			}
		}
		println(hashMap);
	}
}
