package holding.exercise.standard;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import util.TextFile;
import static util.PrintUtil.*;

public class Ex22Code {
	public static void main(String[] args) {
		TextFile words = new TextFile("holding\\SetOperation.java", "\\w+");
		Collections.sort(words, String.CASE_INSENSITIVE_ORDER);
		println("Words to count, sorted: " + words);
		Set<Word> wordObjects = new HashSet<Word>();
		Iterator<String> it = words.iterator();
		while (it.hasNext()) {
			String s = it.next();
			int count = 0;
			for (int i = 0; i < words.size(); i++) {
				if (s.equals(words.get(i)))
					count++;
			}
			Word w = new Word(s, count);
			wordObjects.add(w);
		}
		println("Word count: " + wordObjects);
	}
}

class Word {
	private String name;
	private Integer count;

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