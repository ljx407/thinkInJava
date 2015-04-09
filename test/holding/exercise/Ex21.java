package holding.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import util.TextFile;

import static util.PrintUtil.*;

public class Ex21 {
	
	@Test
	public void testMap() {
		TextFile textFile = new TextFile("holding\\SetOperation.java","\\w+");
		
		Map<String,Integer> linkedHashMap = new LinkedHashMap<String,Integer>();
		
		for(String str : textFile) {
			Integer count = linkedHashMap.get(str);
			linkedHashMap.put(str,  count == null ? 1 : ++count );
		}
		
		Set<String> hashSet = linkedHashMap.keySet();
		List<String> arrayList = new ArrayList<String>(hashSet);
		println(arrayList);
		Collections.sort(arrayList,String.CASE_INSENSITIVE_ORDER);
		println(arrayList);

		for(String str : arrayList) {
			println("key:{} , value:{}" , str ,linkedHashMap.get(str));
		}
	}
}
