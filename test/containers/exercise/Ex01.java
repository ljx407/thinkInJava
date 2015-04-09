package containers.exercise;

import static util.PrintUtil.println;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import util.Countries;

public class Ex01 {
	
	@Test
	public void testArrayList() {
		List<String> arrayList = new ArrayList<String>(Countries.names());
		println(arrayList);
		
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(arrayList);
			println(arrayList);
		}
	}
	
	@Test
	public void testLinkedList() {
		List<String> linkedList = new LinkedList<String>(Countries.names());
		println(linkedList);
		
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(linkedList);
			println(linkedList);
		}
	}
}
