package holding;

import static util.PrintUtil.println;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import util.ArrayUtil;
import util.TextFile;

public class Holding11_09 {
	@Test
	public void setTest() {
		Set<String> set = new HashSet<String>();
		Collections.addAll(set, "A B C D E F G H I J K L".split(" "));
		println(set);
	}

	@Test
	public void treeSetTest() {
		Set<String> set = new TreeSet<String>();
		Collections.addAll(set, "A B C D E F G H I J K L".split(" "));
		println(set);
	}

	@Test
	public void setOperation() {
		Set<String> set = new LinkedHashSet<String>();
		Collections.addAll(set, "A B C D E F G H I J K L".split(" "));
		println(set);
		set.add("M");
		println(set.contains("M"));
		println(set.contains("N"));
		Set<String> set2 = new TreeSet<String>();
		Collections.addAll(set2, "N O P Q".split(" "));
		println(set.contains(set2));
		set.addAll(set2);
		println(set);
		set2.addAll(Arrays.asList("R S".split(" ")));
		println(set.containsAll(set2));
		println(set);
		println(set2);
		set.removeAll(set2);
		println(set);
	}

	@Test
	public void uniqueWordTest() {
		Set<String> hashSet = new TreeSet<String>(new TextFile("holding\\SetOperation.java", "\\w+"));
		println(hashSet);
	}

	@Test
	public void uniqueWordAlphabetic() {
		Set<String> treeSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
//		Collections.addAll(treeSet, new TextFile("holding\\SetOperation.java", "\\w+").toArray(new String[1]));
		treeSet.addAll(new TextFile("holding\\SetOperation.java", "\\w+"));
		println(treeSet);
	}

	@Test
	public void test() {
		Pattern pattern = Pattern.compile("\\w+");
		char[] aa = "abc".toCharArray();
		Character[] aaa = { 'a', 'b', 'c' };
		println(String.valueOf(aaa));
		println(Arrays.toString(aaa));
		println(ArrayUtil.join(aaa));
		Matcher matcher = pattern.matcher(String.valueOf(aa[0]));
		println(matcher.matches());
		println(String.valueOf(aa));
	}

	@Test
	public void test1() {
		Pattern pattern = Pattern.compile("(\\w+)");
		String str = "import static ";
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			println(matcher.group());
		}
	}

	@Test
	public void test2() {
		Pattern pattern = Pattern.compile("([auoi])");
		Matcher matcher = pattern.matcher("word");
		int count = 0;
		while (matcher.find()) {
			count++;
			println(matcher.group());
		}
		println(count);
	}
}