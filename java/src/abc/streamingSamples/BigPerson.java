package abc.streamingSamples;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BigPerson implements Serializable{
	
	private String name;
	
	private int age;
	
	private List<BigPerson> friends = new ArrayList<>();
	
	public BigPerson() {}

	public BigPerson(String name, int age){
		this.name=name;
		this.age=age;
	}
	
	public void addFriends(BigPerson friend){
		friends.add(friend);
	}
	
	
	public void printFriends(){
		for (BigPerson input: friends){
			System.out.println(input);
		}
	}
	
	public String toString (){
		return this.name +" " +this.age;
	}
	
	public void save(){
		try (ObjectOutputStream encode = new ObjectOutputStream (new FileOutputStream("C:\\Users\\YasserAlejandro\\Documents\\Job Search\\bigtest.dat"));)
		{
		  encode.writeObject(friends);	
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	public void capture(){
		try (ObjectInputStream incode = new ObjectInputStream(new FileInputStream("C:\\Users\\YasserAlejandro\\Documents\\Job Search\\bigtest.dat"));)
		{
			friends = (List<BigPerson>)incode.readObject();
		} catch (IOException | ClassNotFoundException ex){
			ex.printStackTrace();
		}
	}
	
	
	
}
