package abc.streamingSamples;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class Person implements Serializable {
	
	private String name;
	
	private int age;
	
	private List<String> nicknames = new LinkedList<>();

	public Person(){
		//super();
	}
	
	
	public Person(String name, int age){
		this.name=name;
		this.age=age;
		nicknames = new LinkedList<>();
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void addNickname(String nickname){
		nicknames.add(nickname);
	}
	
	@Override
	public String toString(){
		return this.name + " "+this.age;
	}
	
	public void printNicknames(){
		for (String input: nicknames){
			System.out.println(input);
		}
	}
	
	

}
