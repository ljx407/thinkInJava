package holding.exercise;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import static util.PrintUtil.*;

public class Ex25 {
	
	@Test
	public void mapTest() {
		String filePath = "G:\\workspaceSTS\\thinkingInJava\\test\\holding\\SetOperation.java";
		FileInputStream fileInputStream = null ;
		BufferedReader bufferReader = null ;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			fileInputStream = new FileInputStream(filePath);
			bufferReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String line = null ;
			
			Pattern pattern = Pattern.compile("(\\w+)");
			Matcher matcher = null ;
			
			Set<String> words = new LinkedHashSet<String>();
			while((line = bufferReader.readLine()) != null) {
				stringBuffer.append(line);
				matcher = pattern.matcher(line);
				while(matcher.find()) {
					words.add(matcher.group());
				}
			}
			
			String fileText = stringBuffer.toString();
			Iterator<String> iterator = words.iterator();
			Map<String,List<Integer>> linkedHashMap = new LinkedHashMap<String,List<Integer>>();
			while(iterator.hasNext()) {
				String str = iterator.next();
				pattern = Pattern.compile(str, Pattern.MULTILINE);
				matcher = pattern.matcher(fileText);
				while(matcher.find()) {
					List<Integer> coordinate = linkedHashMap.get(str) ;
					if(coordinate == null) {
						coordinate = new LinkedList<Integer>();
					}
					coordinate.add(matcher.start());
					linkedHashMap.put(str, coordinate);
				}
			}
			println(linkedHashMap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fileInputStream != null) {
				try {
					fileInputStream.close();
					fileInputStream = null ;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}
