package holding.exercise.standard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import util.TextFile;
import static util.PrintUtil.*;

public class Ex21Code {
	public static void main(String[] args) {
		List<String> words = new ArrayList<String>(new TextFile("holding\\SetOperation.java", "\\w+"));
		println("Words to count: " + words);
		Collections.sort(words, String.CASE_INSENSITIVE_ORDER);
		Map<String, Integer> wordCount = new LinkedHashMap<String, Integer>();
		Iterator<String> it = words.iterator();
		int totalWords = 0;
		while (it.hasNext()) {
			String s = it.next();
			if (words.contains(s)) {
				Integer count = wordCount.get(s);
				wordCount.put(s, count == null ? 1 : count + 1);
				totalWords++;
			}
		}
		println();
		println("Word count: " + wordCount);
		println();
		println("Total words: " + totalWords);
	}
}