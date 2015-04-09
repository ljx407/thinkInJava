package containers.exercise.standard;

//containers/Ex7.java
//TIJ4 Chapter Containers, Exercise 7, page 820
/* Create both an ArrayList and a LinkedList, and fill each using the
*  Countries.names() generator. Print each list using an ordinary 
* iterator, then insert one list into the other by using a ListIterator, 
* inserting at every other location. Now perform the insertion staring
* at the end of the first list and moving backwards.
*/
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import util.Countries;

import static util.PrintUtil.*;

public class Ex07Code {	
	@SuppressWarnings({ "unchecked", "rawtypes" })	
	public static void main(String[] args) {
		List<String> al = new ArrayList<String>(Countries.names(25));
		List<String> ll = new LinkedList<String>(Countries.names(25));
		print(al);
		print(ll);		
		Iterator alit = al.iterator();
		Iterator llit = ll.iterator();
		while(alit.hasNext()) {
			println(alit.next() + (alit.hasNext() ? ", " : ""));
		}
		println();
		while(llit.hasNext()) {
			println(llit.next() + (llit.hasNext() ? ", " : ""));
		}
		println();
		println();
		ListIterator allit = al.listIterator();
		ListIterator lllit = ll.listIterator();		
		while(lllit.hasNext()) {			
			allit.add((String)lllit.next());
			allit.next();
		}
		print(al);
		println();
		List<String> al2 = new ArrayList<String>(Countries.names(25));
		ListIterator allit2 = al2.listIterator();
		while(lllit.hasPrevious()) lllit.previous();
		while(allit2.hasNext()) allit2.next();
		while(lllit.hasNext()) {
			allit2.add((String)lllit.next());
			allit2.previous();
			allit2.previous();
		}
		print(al2);
	}
}
