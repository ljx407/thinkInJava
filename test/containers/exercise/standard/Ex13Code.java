package containers.exercise.standard;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import util.TextFile;
import containers.Containers_08_map;
import containers.Containers_08_map.AssociationArray;
import static util.PrintUtil.*;

public class Ex13Code {
	public static void main(String[] args) {
		Containers_08_map map = new Containers_08_map();
		// File whose words are to be counted:
		String fileName = "WordCounter13.java";
		// Set of unique words in file:
		Set<String> words = new TreeSet<String>(new TextFile(fileName, "\\w+"));
		// Create initialize array of correct length:
		AssociationArray<String,Integer> wordCount =
				map.new AssociationArray<String,Integer>(words.size());
		// Word list of all words in file:
		ArrayList<String> fileList = new TextFile(fileName, "\\w+");
		// Count appearances of each unique word and add to array:
		for(String s : words) {
			int count = 0;
			for(String t : fileList) {
				if(t.equals(s)) count++;
			}
			wordCount.put(s, count);
		}
		print(wordCount);
	}
}
