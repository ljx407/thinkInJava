package containers.exercise;

import org.junit.Test;

import util.TextFile;
import containers.Containers_08_map;
import containers.Containers_08_map.AssociationArray;
import static util.PrintUtil.*;

public class Ex13 {
	
	private Containers_08_map map = new Containers_08_map();

	@Test
	public void test() {
		TextFile textFile = new TextFile("holding\\SetOperation.java","\\w+");
		AssociationArray<String,Integer> aa = map.new AssociationArray<String,Integer>(textFile.size());
		if(textFile != null && !textFile.isEmpty()) {
			for(String s : textFile) {
				Integer count = aa.get(s);
				aa.put(s, count == null ? 1 : ++count);
			}
		}
		println(aa);
		
	}
}
