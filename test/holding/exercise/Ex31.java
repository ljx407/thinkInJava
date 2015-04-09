package holding.exercise;

import static util.PrintUtil.*;

import java.util.Iterator;
import java.util.Random;

import org.junit.Test;

public class Ex31 {
	
	@Test
	public void testIterable() {
		RandomShapeGenerator shapes = new RandomShapeGenerator(5);
		for(Shape shape : shapes) {
			shape.draw();
		}
	}
	
}

class RandomShapeGenerator implements Iterable<Shape> {

	private Random rand = new Random();

	private int length = 0;

	private Shape[] shapes = null ;

	public RandomShapeGenerator() {
		super();
	}

	public RandomShapeGenerator(int length) {
		this.length = length;
		shapes = new Shape[length];
		for (int i = 0; i < length; i++) {
			shapes[i] = next();
		}
	}

	public Shape next() {
		switch (rand.nextInt(3)) {
		default:
		case 0:
			return new Circle();
		case 1:
			return new Square();
		case 2:
			return new Triangle();
		}
	}

	@Override
	public Iterator<Shape> iterator() {
		return new Iterator<Shape>() {
			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < length;
			}

			@Override
			public Shape next() {
				return shapes[index++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

		};
	}

}

class Shape {
	public void draw() {
	}

	public void erase() {
	}
}

class Square extends Shape {
	@Override
	public void draw() {
		println("Square.draw()");
	}

	@Override
	public void erase() {
		println("Square.erase()");
	}
}

class Triangle extends Shape {
	@Override
	public void draw() {
		println("Triangle.draw()");
	}

	@Override
	public void erase() {
		println("Triangle.erase()");
	}
}

class Circle extends Shape {
	@Override
	public void draw() {
		println("Circle.draw()");
	}

	@Override
	public void erase() {
		println("Circle.erase()");
	}
}
