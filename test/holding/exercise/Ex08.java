package holding.exercise;import java.util.ArrayList;import java.util.Collections;import java.util.Iterator;import java.util.List;import org.junit.Test;import static util.PrintUtil.*; public class Ex08 {		@Test	public void testIterator() {		List<Gerbil> gerbils = new ArrayList<Gerbil>();		Collections.addAll(gerbils, new Gerbil(1) , new Gerbil(2));				Iterator<Gerbil> iterator = gerbils.iterator();		while(iterator.hasNext()) {			Gerbil gerbil = iterator.next();			println(gerbil.hop());		}	}}class Gerbil {	private int gerbilNumber;	public Gerbil(int gerbilNumber) {		super();		this.gerbilNumber = gerbilNumber;	}	public String hop() {		return "gerbil " + gerbilNumber + " is jumping !";	}	public int getGerbilNumber() {		return gerbilNumber;	}	public void setGerbilNumber(int gerbilNumber) {		this.gerbilNumber = gerbilNumber;	}	@Override	public String toString() {		return "Gerbil [gerbilNumber=" + gerbilNumber + "]";	}		}