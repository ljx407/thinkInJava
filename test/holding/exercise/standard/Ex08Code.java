package holding.exercise.standard;

import static util.PrintUtil.*;
import java.util.ArrayList;
import java.util.Iterator;

class Gerbil {
	private int gerbilNumber;

	public Gerbil(int i) {
		gerbilNumber = i;
	}

	public void hop() {
		println("Gerbil " + gerbilNumber + " hops");
	}
}

public class Ex08Code {
	public static void main(String[] args) {
		ArrayList<Gerbil> gerbils = new ArrayList<Gerbil>();
		for (int i = 0; i < 10; i++)
			gerbils.add(new Gerbil(i));
		Iterator<Gerbil> it = gerbils.iterator();
		while (it.hasNext())
			it.next().hop();
	}
}