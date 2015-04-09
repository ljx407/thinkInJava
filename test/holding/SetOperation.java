package holding;

import static util.PrintUtil.println;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetOperation {
	public static void main(String[] args) {
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
}
