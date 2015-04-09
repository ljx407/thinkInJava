package holding.p3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AddingGroup {

	public static void main(String[] args) {
		Collection<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
		System.out.println(arrayList);

		Collection<Integer> arrayList1 = new ArrayList<Integer>();
		arrayList1.addAll(Arrays.asList(1, 2, 3, 4, 5));
		System.out.println(arrayList1);

		Collection<Integer> arrayList2 = new ArrayList<Integer>();
		Collections.addAll(arrayList2, 1, 2, 3, 4, 5);
		System.out.println(arrayList2);

		Integer[] constructArgs = { 1, 2, 3, 4, 5 };
		Collection<Integer> arrayList3 = new ArrayList<Integer>();
		Collections.addAll(arrayList3, constructArgs);
		System.out.println(arrayList3);
		
		List<Integer> elementList = Arrays.asList(10,11,12,13,14);
		System.out.println(elementList);
		elementList.set(0, 15);
		System.out.println(elementList);
		
//		elementList.add(16);
//		System.out.println(elementList);

		Collection<Integer> arrayList4 = new ArrayList<Integer>();
		AddingGroup.addAllTest(arrayList4, 6, 6, 8, 9);
		System.out.println(arrayList4);
	}

	@SafeVarargs
	public static <T> boolean addAllTest(Collection<? super T> c, T... elements) {
		boolean result = false;
		for (T element : elements)
			result |= c.add(element);
		return result;
	}

}
