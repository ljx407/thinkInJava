package containers.exercise;

import static util.PrintUtil.println;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import util.Countries;

public class Ex02 {
	
	@Test
	public void test() {
		Map<String,String> linkedHashMap = Countries.capitals();
		List<String> arrayList=  new ArrayList<String>();
		Pattern pattern = Pattern.compile("a");
		Matcher matcher  = null;
		for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
			matcher = pattern.matcher(entry.getKey().toLowerCase());
			if(matcher.lookingAt()) {
				arrayList.add(entry.getKey());
			}
		}
		println(arrayList);
	}
}
