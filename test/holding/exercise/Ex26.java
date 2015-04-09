package holding.exercise;

import static util.PrintUtil.println;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import util.TextFile;

public class Ex26 {
	
	@Test
	public void mapTest() {
		TextFile textFile = new TextFile("holding\\SetOperation.java","\\w+");
		Set<String> set = new HashSet<String>();
		set.addAll(textFile);
		
		Iterator<String> iterator = set.iterator();
		String text = textFile.getFileText();
		Map<String,List<Integer>> linkedHashMap = new LinkedHashMap<String,List<Integer>>();	
		while(iterator.hasNext()) {
			String regex = iterator.next();
			Pattern pattern = Pattern.compile("("+regex+")");
			Matcher matcher = pattern.matcher(text);
			while(matcher.find()) {
				List<Integer> coordinate = linkedHashMap.get(regex);
//				if(coordinate == null) {
//					coordinate = new LinkedList<Integer>();
//				}
				if(!linkedHashMap.containsKey(regex)) {
					coordinate = new LinkedList<Integer>();
				}
				coordinate.add(matcher.start());
				linkedHashMap.put(regex, coordinate);
			}
		}
		println(linkedHashMap);
		
		Map<Integer,String> linkedHashMap1 = new LinkedHashMap<Integer,String>();
		
		for(Map.Entry<String, List<Integer>> entry : linkedHashMap.entrySet()) {
			String key = entry.getKey();
			for(Integer i : entry.getValue()) {
				linkedHashMap1.put(i, key);
			}
		}
		
		Set<Integer> keyOrdered = new TreeSet<Integer>(linkedHashMap1.keySet());
		List<String> linkedList = new LinkedList<String>();
		Iterator<Integer> keyIterator = keyOrdered.iterator();
		while(keyIterator.hasNext()) {
			linkedList.add(linkedHashMap1.get(keyIterator.next()));
		}
		
		println(linkedList);
		
	}
}


















