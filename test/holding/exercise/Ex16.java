package holding.exercise;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import util.TextFile;
import static util.PrintUtil.*;

public class Ex16 {
	
	@Test
	public void setTest() {
		Set<String> treeSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
//		Collections.addAll(treeSet,new TextFile("G:\\workspaceSTS\\thinkingInJava\\test\\holding\\SetOperation.java","\\w+").toArray(new String[1]));
		treeSet.addAll(new TextFile("holding\\SetOperation.java","\\w+"));
		
		Set<String> alphabetic = new HashSet<String>();
		Collections.addAll(alphabetic, "a","u","o","i");
		
		StringBuffer stringBuffer = new StringBuffer();
		Iterator<String> iterator = alphabetic.iterator();
		while(iterator.hasNext()) {
			stringBuffer.append(iterator.next());
		}
		
		Pattern pattern = Pattern.compile("["+stringBuffer.toString()+"]");
		
		Iterator<String> iterator1 = treeSet.iterator();
		Matcher matcher = null;	
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		Integer countTotal = 0 ;
		while(iterator1.hasNext()) {
			String str = iterator1.next();
			matcher = pattern.matcher(str.toLowerCase());
			Integer count = 0 ;
			while(matcher.find()) {
				count = map.get(str) ;
				map.put(str,  count == null ? 1 : ++count );
				countTotal ++ ;
			}
			if(!map.containsKey(str)) {
				map.put(str, 0);
			}
			
		}
		
		println(map);
		println("total aphlabetic num : {}" ,countTotal);
	}
}
