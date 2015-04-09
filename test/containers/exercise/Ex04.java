package containers.exercise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import util.TextFile;
import static util.PrintUtil.*;

public class Ex04 {
	
	@Test
	public void test() {
		List<String> arrayList = new ArrayList<String>();
		generator(arrayList);
		println(arrayList);
	}
	
	public void generator(Collection<String> collection) {
		TextFile textFile = new TextFile("holding\\SetOperation.java", "\\w+");
		collection.addAll(textFile);
	}
	
}
