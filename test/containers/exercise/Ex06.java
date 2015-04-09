package containers.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static util.PrintUtil.*;

public class Ex06 {
	
	@Test
	public void unsupport() {
		List<String> list = Arrays.asList("A B C D E F G H I J".split(" "));
		List<String> subList = new ArrayList<String>(Arrays.asList("X Y Z".split(" ")));
		try {
			list.add("K");
		} catch (Exception e) {
			println("list.add():{}",e);
		}
		
		try {
			list.add(2,"M");
		} catch (Exception e) {
			println("list.add():{}",e);
		}
		
		try {
			list.addAll(subList);
		} catch (Exception e) {
			println("list.addAll():{}",e);
		}
		
		try {
			list.addAll(2, subList);
		} catch (Exception e) {
			println("list.addAll():{}",e);
		}
		
		try {
			list.clear();
		} catch (Exception e) {
			println("list.clear():{}",e);
		}
		
		try {
			list.remove(1);
		} catch (Exception e) {
			println("list.remove():{}",e);
		}
		
		try {
			list.retainAll(subList);
		} catch (Exception e) {
			println("list.ratainAll():{}",e);
		}
		
		try {
			list.removeAll(subList);
		} catch (Exception e) {
			println("list.removeAll():{}",e);
		}
		
		try {
			list.set(3,"W");
		} catch (Exception e) {
			println("list.removeAll():{}",e);
		}
	}
}
