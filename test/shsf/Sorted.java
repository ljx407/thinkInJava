package shsf;

import static util.PrintUtil.println;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class Sorted {

	LinkedList<Integer> linkedList = null;

	@Before
	public void before() {
		linkedList = new LinkedList<Integer>();
		Random random = new Random(693);
		for (int i = 0; i < 20; i++) {
			linkedList.add(random.nextInt(50));
		}
		println(linkedList);
	}

	@Test
	public void testSelectingSort() {
		LinkedList<Integer> copyLinkedList = new LinkedList<Integer>();
		int size = linkedList.size();
		for (int i = 0; i < size; i++) {
			Integer min = Collections.min(linkedList);
			copyLinkedList.addLast(min);
			linkedList.remove(min);
		}
		println(copyLinkedList);
	}

	@Test
	public void testBubbleSort() {
		int size = linkedList.size();
		for (int i = 0; i < size; i++) {
			Integer min = Collections.min(linkedList.subList(i, size));
			int minIndex = linkedList.indexOf(min);
			Collections.swap(linkedList, i, minIndex);
		}
		println(linkedList);
	}

}
