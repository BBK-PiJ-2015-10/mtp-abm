package abc.streamingSamples;

public class BigPersonWritingTest {

	public static void main(String[] args) {
		
		BigPersonWritingTest script1 =  new BigPersonWritingTest();
		script1.launch();
	
	}
	
	public void launch(){
		
		/*
		
		BigPerson first = new BigPerson("Ale",33);
		
		BigPerson f1 = new BigPerson("Leon",10);
		BigPerson f2 = new BigPerson("Rabbo",20);
		
		first.addFriends(f1);
		first.addFriends(f2);
		
		first.printFriends();
		first.save();
		
		 */
		
		BigPerson newone = new BigPerson();
		newone.printFriends();
		System.out.println("I have no friends");
		newone.capture();
		newone.printFriends();
		
	}

}
