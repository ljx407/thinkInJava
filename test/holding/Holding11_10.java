package holding;

import static util.PrintUtil.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.junit.Test;

public class Holding11_10 {
	
	@Test
	public void mapTest() {
		
		Random random = new Random(new Date().getTime());
		
		Map<Integer,Integer> ranMap = new HashMap<Integer,Integer>();
		
		for (int i = 0; i < 10000; i++) {
			Integer ran = random.nextInt(20);
			Integer ranValue = ranMap.get(ran);
			ranMap.put(ran, ranValue == null ? 1 : ++ranValue);
		}
		println(ranMap);
	}
	
	@Test
	public void treeMapTest1() {
		Map<String,Pet> petMap = new TreeMap<String,Pet>();
		petMap.put("my dog", new Dog("dog"));
		petMap.put("my cat", new Cat("cat"));
		petMap.put("my tiger", new Tiger("tiger"));
		
		Pet dog = petMap.get("my dog");
		println(dog);
		
		println(petMap.containsKey("my dog"));
		println(petMap.containsKey("my lion"));
		
		println(petMap.containsValue(new Dog("dog")));
		println(petMap.containsValue(dog));
		println(petMap.containsValue(new Lion("lion")));
	}
	
	@Test
	public void linkedHashMapTest() {
		Map<Person,List<Pet>> linkedHashMap = new LinkedHashMap<Person,List<Pet>>();
		linkedHashMap.put(new Person("A"), Arrays.asList(new Dog("ADog1"),new Cat("ACat1")));
		
		List<Pet> bPets = new ArrayList<Pet>();
		Collections.addAll(bPets, new Tiger("BTiger") , new Lion("BLion"));
		linkedHashMap.put(new Person("B"), bPets);
		
		println(linkedHashMap);
		
		println(linkedHashMap.keySet());
		
		println(linkedHashMap.values());
		
		for(Person person : linkedHashMap.keySet()) {
			print(person + " " + linkedHashMap.get(person));
		}
		println("");
		for(Map.Entry<Person,List<Pet>> entry : linkedHashMap.entrySet()) {
			print("key : {} , value {} " ,entry.getKey() , entry.getValue() ) ;
		}
		
	}
	
}

class Person {
	private int id ;
	private String name ;
	
	public Person(String name) {
		this(0,name);
	}
	
	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
