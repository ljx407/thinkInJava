package util;

import java.util.Random;

public class CountingGenerator {
	
	public static class Character implements Generator<java.lang.Character> {
		private char[] chaf = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','v','w','u','x','y','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','V','W','U','X','Y'};

		@Override
		public java.lang.Character next() {
			Random random = new Random();
			return chaf[random.nextInt(chaf.length)];
		}
		
	}
}
