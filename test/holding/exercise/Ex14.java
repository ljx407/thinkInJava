package holding.exercise;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import static util.PrintUtil.*;

import org.junit.Test;

public class Ex14 {

	@Test
	public void testLinkedList() {
		List<Integer> linkedList = new LinkedList<Integer>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int size = linkedList.size();
			int randomNum = random.nextInt(20);
			if(size > 1) {
				linkedList.add(size/2,randomNum);
			} else {
				linkedList.add(randomNum);
			}
			println("randomNum:{},linkedList{}" , randomNum ,linkedList );
		}
		println(linkedList);
	}
	
	@Test
	public void testLinkedList1() {
		List<Integer> linkedList = new LinkedList<Integer>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			insertNum(linkedList,random.nextInt(20));
		}
	}
	
	public void insertNum(List<Integer> lists , int randomNum) {
		ListIterator<Integer> listIterator = lists.listIterator();
		while(lists.size()/2 <= 0 || listIterator.hasNext()) {
			if(lists.size()/2 <= 0 ) {
				listIterator.add(randomNum);
				println("randomNum:{},linkedList:{}",randomNum,lists);
				return ;
			} 
			if(listIterator.nextIndex() == lists.size()/2) {
				listIterator.add(randomNum);
				println("randomNum:{},linkedList:{}",randomNum,lists);
			}
			listIterator.next();
		}
	}
}
