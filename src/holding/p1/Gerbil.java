package holding.p1;

import java.util.ArrayList;
import java.util.List;

public class Gerbil {

	private int gerbilNumber;

	public Gerbil(int gerbilNumber) {
		super();
		this.gerbilNumber = gerbilNumber;
	}

	public String hop() {
		return "gerbil " + gerbilNumber + " is jumping !";
	}

	public static void main(String[] args) {
		List<Gerbil> gerbils = new ArrayList<Gerbil>() ;
		gerbils.add(new Gerbil(1));
		gerbils.add(new Gerbil(2));
		if(gerbils!=null && !gerbils.isEmpty()){
			for(Gerbil gerbil : gerbils) {
				System.out.println(gerbil.hop());
			}
		}
	}
	
	public int getGerbilNumber() {
		return gerbilNumber;
	}

	public void setGerbilNumber(int gerbilNumber) {
		this.gerbilNumber = gerbilNumber;
	}

}
