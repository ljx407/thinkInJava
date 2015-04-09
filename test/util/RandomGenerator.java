package util;

import java.util.Random;

public class RandomGenerator  {
	
	public static class String implements Generator<java.lang.String> {
		
		private int length ;
		
		private char[] chaf = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','v','w','u','x','y','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','V','W','U','X','Y'};

		public String(int length) {
			this.length = length;
		}

		@Override
		public java.lang.String next() {
			Random random = new Random();
			java.lang.String result = "" ;
			for (int i = 0; i < length; i++) {
				int index = random.nextInt(chaf.length);
				result += java.lang.String.valueOf(chaf[index]) ;
			}
			return result;
		}
		
		
	}
	
	public static class Integer implements Generator<java.lang.Integer> {
		
		@Override
		public java.lang.Integer next() {
			Random random = new Random();
			return random.nextInt(10000);
		}
		
	}
}



