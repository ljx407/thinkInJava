package holding.exercise.standard;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import util.TextFile;
import static util.PrintUtil.*;

public class Ex16Code {
	static void vowelCounter(Set<String> st) {
		Set<Character> vowels = new TreeSet<Character>();
		Collections.addAll(vowels, 'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u');
		int allVowels = 0;
		for (String s : st) {
			int count = 0;
			for (Character v : s.toCharArray()) {
				if (vowels.contains(v)) {
					count++;
					allVowels++;
				}
			}
			print(s + ": " + count + ", ");
		}
		println();
		print("Total vowels: " + allVowels);
	}

	public static void main(String[] args) {
		Set<String> words = new TreeSet<String>(new TextFile("holding\\SetOperation.java", "\\w+"));
		println(words);
		println();
		vowelCounter(words);
	}
}