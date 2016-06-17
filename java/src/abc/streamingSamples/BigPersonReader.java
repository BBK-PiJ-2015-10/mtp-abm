package abc.streamingSamples;

public class BigPersonReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BigPersonReader script2 = new BigPersonReader();
		script2.launch();

	}
	
	public void launch(){
		
		BigPerson test2 = new BigPerson("yasser",40);
		test2.printFriends();
		
		System.out.println("I have no friends");
		
		test2.capture();
		
		System.out.println("Wait, I am becoming mr.butterfly");
		test2.printFriends();
		
		BigPerson newguy = new BigPerson("clementina",30);
		test2.addFriends(newguy);
		test2.save();
		
		
	}

}
