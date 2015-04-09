package containers.exercise;

import static util.PrintUtil.println;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

import util.RandomGenerator;

public class Ex09 {

	@Test
	public void test() {
		SortedSet<String> sortedSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		for (int i = 0; i < 6; i++) {
			sortedSet.add(new RandomGenerator.String(6).next());
		}
		println(sortedSet);
	}
	
}
