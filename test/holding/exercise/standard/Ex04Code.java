package holding.exercise.standard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import static util.PrintUtil.*;

class Generator {
	int key = 0;

	public String next() {
		String result = "";
		switch (key) {
		case 0:
			key++;
			result = "0";
			break;
		case 1:
			key++;
			result = "1";
			break;
		case 2:
			key++;
			result = "2";
			break;
		case 3:
			key++;
			result = "3";
			break;
		case 4:
			key++;
			result = "4";
			break;
		case 5:
			key++;
			result = "5";
			break;
		case 6:
			key++;
			result = "6";
			break;
		case 7:
			key++;
			result = "7";
			break;
		case 8:
			key++;
			result = "8";
			break;
		case 9:
			key++;
			result = "9";
			break;
		case 10:
			key = 0;
			result = "A";
			break;
		}
		return result;
	}
}

public class Ex04Code {
	public Ex04Code(String[] arrays) {
		Generator g1 = new Generator();
		for (int i = 0; i < arrays.length; i++) {
			arrays[i] = g1.next();
		}
		println(Arrays.asList(arrays));
	}

	public Ex04Code(Collection<String> c, int n) {
		Generator g1 = new Generator();
		for (int i = 0; i < n; i++) {
			c.add(g1.next());
		}
		println(c);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Ex04Code ex4Code1 = new Ex04Code(new ArrayList<String>(), 10);
		Ex04Code ex4Code2 = new Ex04Code(new LinkedList<String>(), 10);
		Ex04Code ex4Code3 = new Ex04Code(new HashSet<String>(), 10);
		Ex04Code ex4Code4 = new Ex04Code(new TreeSet<String>(), 10);
		Ex04Code ex4Code5 = new Ex04Code(new LinkedHashSet<String>(), 10);
		Ex04Code ex4Code6 = new Ex04Code(new String[10]);
	}
}