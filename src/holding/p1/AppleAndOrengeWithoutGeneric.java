package holding.p1;

import java.util.ArrayList;
import java.util.List;

public class AppleAndOrengeWithoutGeneric {
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public static void main(String[] args) {
		List list = new ArrayList() ; 
		list.add(new Apple(1));
		list.add(new Orange());
		
		if(list != null && !list.isEmpty()) {
			for(Object obj : list) {
				System.out.println(((Apple)obj).getId());
			}
		}
		
//		int[] a = new int[3];
//		a[0] = 1;
//		a[1] = 2;
//		a[2] = 3;
//		int[] aa = {1,2,3};
//		
//		System.out.println(a.equals(aa));
	}
}

class Apple {
	int id ;
	
	public Apple(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

class Orange {}