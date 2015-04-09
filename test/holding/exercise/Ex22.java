package holding.exercise;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import util.TextFile;

import static util.PrintUtil.*;

public class Ex22 {
	
	@Test
	public void mapTest() {
		TextFile textFile = new TextFile("holding\\SetOperation.java","\\w+");
		Map<String,Integer> linkedHashMap = new LinkedHashMap<String,Integer>();
		Iterator<String> iterator = textFile.iterator();
		while(iterator.hasNext()) {
			String str = iterator.next();
			Integer count = linkedHashMap.get(str);
			linkedHashMap.put(str, count==null ? 1 : ++count);
		}
		
		Set<Word> linkedHashSet = new LinkedHashSet<Word>();
		for(Map.Entry<String, Integer> entry : linkedHashMap.entrySet()) {
			Word words = new Word(entry.getKey(),entry.getValue());
			linkedHashSet.add(words);
		}
		
		println(linkedHashSet);
	}
}

class Word {
	private String name ;
	private Integer count ;
	
	public Word(String name, Integer count) {
		this.name = name;
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Word [name=" + name + ", count=" + count + "]";
	}
}
