package containers.exercise;

import static util.PrintUtil.println;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import util.Countries;

public class Ex03 {
	
	@Test
	public void test() {
		Set<String> hashSet = new HashSet<String>(Countries.names());
		Set<String> treeSet = new TreeSet<String>(Countries.names());
		Set<String> linkedHashSet = new LinkedHashSet<String>(Countries.names());
		
		println("hashSet : {}" , hashSet);
		println("treeSet : {}" , treeSet);
		println("linkedHashSet : {}" , linkedHashSet);
		
		//fill set more than one times 
		for (int i = 0; i < 5; i++) {
//			Collections.addAll(hashSet, Countries.names(i).toArray(new String[0]));
//			Collections.addAll(treeSet, Countries.names(i).toArray(new String[0]));
//			Collections.addAll(linkedHashSet, Countries.names(i).toArray(new String[0]));
			
			hashSet.addAll(Countries.names(i));
			treeSet.addAll(Countries.names(i));
			linkedHashSet.addAll(Countries.names(i));
		}
		
		println("hashSet : {}" , hashSet);
		println("treeSet : {}" , treeSet);
		println("linkedHashSet : {}" , linkedHashSet);
		
		//judge whether equals or not 
		boolean hashSetResult = isExistSameElement(hashSet);
		boolean treeSetResult = isExistSameElement(treeSet);
		boolean linkedHashSetResult = isExistSameElement(linkedHashSet);
		
		println("hashSetResult : {}" , hashSetResult) ;
		println("treeSetResult : {}" , treeSetResult) ;
		println("linkedHashSetResult : {}" , linkedHashSetResult) ;
		
	}
	
	public boolean isExistSameElement(Set<String> set) {
		boolean result = false ;
		Map<String,Integer> linkedHashMap = new LinkedHashMap<String,Integer>();
		//count statistic
		for(String s : set) {
			linkedHashMap.put(s, linkedHashMap.get(s) == null ? 1 : linkedHashMap.get(s)+1);
		}
		//judge the count 
		for(Map.Entry<String, Integer> entry : linkedHashMap.entrySet() ) {
			if(entry.getValue() > 1) {
				result = true ;
				break ;
			}
		}
		return result ;
	}
}
