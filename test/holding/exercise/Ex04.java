package holding.exercise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import static util.PrintUtil.*;

public class Ex04 {
	List<String> list;
	Set<String> set;
	String[] array;
	int countList = 0;
	int countSet = 0;
	int countArray = 0;

	public Ex04(String[] array) {
		this.array = array;
		initArray();
	}

	public Ex04(List<String> list) {
		this.list = list;
		initList();
	}

	public Ex04(Set<String> set) {
		this.set = set;
		initSet();
	}

	public void initList() {
		if (list != null) {
			list.add("snow white");
			list.add("star wars");
			list.add("hiber");
		}
	}

	public void initSet() {
		if (set != null) {
			set.add("snow white");
			set.add("star wars");
			set.add("hiber");
		}
	}

	public void initArray() {
		if (array != null) {
			array[0] = "snow white";
			array[1] = "star wars";
			array[2] = "hiber";
		}
	}

	public String nextList() {
		String result = "";
		if (list != null && !list.isEmpty()) {
			result = list.get(countList);
			countList++;
			if (countList >= list.size()) {
				countList = 0;
			}
		}
		return result;
	}

	public String nextSet() {
		String result = "";
		if (set != null && !set.isEmpty()) {
			result = (String) set.toArray()[countSet];
			countList++;
			if (countSet >= set.size()) {
				countList = 0;
			}
		}
		return result;
	}

	public String nextArray() {
		String result = "";
		if (array != null && !list.isEmpty()) {
			result = array[countArray];
			countList++;
			if (countArray >= array.length) {
				countArray = 0;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Ex04 ex41 = new Ex04(new ArrayList<String>());
		println(ex41.list);
		Ex04 ex42 = new Ex04(new LinkedList<String>());
		println(ex42.list);
		Ex04 ex43 = new Ex04(new HashSet<String>());
		println(ex43.set);
		Ex04 ex44 = new Ex04(new TreeSet<String>());
		println(ex44.set);
		Ex04 ex45 = new Ex04(new LinkedHashSet<String>());
		println(ex45.set);
		Ex04 ex46 = new Ex04(new String[3]);
		println(ex46.array);
	}
}