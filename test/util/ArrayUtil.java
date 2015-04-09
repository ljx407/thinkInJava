package util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class ArrayUtil {
	
	public static String join(Object[] obj) {
		return join(obj,""); 
	}
	
	public static String join(Object[] obj , String flag ) {
		String returnStr = method2(Arrays.toString(obj),flag);
		return returnStr.substring(1, returnStr.length()-1);
	}
	
	private static String method1(String str, String flag) {
		Pattern pattern = Pattern.compile(Pattern.quote(", "));
		Matcher matcher = pattern.matcher(str);
		return matcher.replaceAll(Matcher.quoteReplacement(flag));
	}
	
	private static String method2(String str, String flag) {
		return str.replaceAll(Pattern.quote(", "), Matcher.quoteReplacement(flag));
	}
}
