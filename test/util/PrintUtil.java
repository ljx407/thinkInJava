package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrintUtil {
	public static void println() {
		System.out.println(" ");
	}

	public static void println(Object t) {
		System.out.println(t);
	}

	public static void print(Object t) {
		System.out.print(t);
	}

	public static void println(String str, Object... t) {
		if (str != null && t.length > 0) {
			for (int i = 0; i < t.length; i++) {
				str = str.replaceFirst(Pattern.quote("{}"), Matcher.quoteReplacement(t[i].toString()));
			}
			System.out.println(str);
		}
	}

	public static void print(String str, Object... t) {
		if (str != null && t.length > 0) {
			for (int i = 0; i < t.length; i++) {
				str = str.replaceFirst("\\{\\}", t[i].toString());
			}
			System.out.print(str);
		}
	}
}
