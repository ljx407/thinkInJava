package temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import static util.PrintUtil.*;

public class Temp {
	
	public static void main(String[] args) {
		int i = 0;
		i = i++;
		println(i);
	}
	@Test
	public void 丢手绢问题方法1() {
		int size = 4;
		int num = 5;
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 1; i <= size; i++) {
			list.add(i);
		}
		
		int counter = 1;
		while (!list.isEmpty()) {
			Iterator<Integer> itr = list.iterator();
			List<Integer> outer = new ArrayList<Integer>();
			while (itr.hasNext()) {
				Integer next = itr.next();
				System.out.println(counter + ":" + next);
				if (counter++ == num) {
					counter = 1;
					System.out.println(next);
					outer.add(next);
				}
			}
			list.removeAll(outer);
			outer.clear();
		}
	}
	
	@Test
	public void 丢手绢问题方法2() {
		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		for (int i = 1; i <= 4; i++) {
			map.put(i, true);
		}
		System.out.println(map);
		
		int counter = 1;
		while (map.containsValue(true)) {
			Set<Integer> keySet = map.keySet();
			for (Integer i : keySet) {
				if (map.get(i)) {
					System.out.println(counter + ":" + i);
					if (counter++ == 2) {
						counter = 1;
						System.out.println(i);
						map.put(i, false);
					}
				}
			}
		}
	}
	
	@Test
	public void 丢手绢问题方法3() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 4; i++) {
			list.add(i);
		}
		println(list);

		int counter = 0;
		while (!list.isEmpty()) {
			Iterator<Integer> itr = list.iterator();
			while(itr.hasNext()) {
				counter = ++counter % 2 ;
				println(counter);
				Integer next = itr.next();
				println("{}报数:{}",next,counter);
				if(counter != 1) {
					println("{}出队!",next);
					itr.remove();
				}
			}
		}
	}
}
