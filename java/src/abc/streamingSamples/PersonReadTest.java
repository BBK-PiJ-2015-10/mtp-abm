package abc.streamingSamples;

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class PersonReadTest {

	public static void main(String[] args) {
		
		PersonReadTest script = new PersonReadTest();
		script.launch();

	}
	
	public void launch(){
		
		ObjectInputStream ois = null;
		
		Person pl = null;
		
		try {
			
			ois = new ObjectInputStream(new FileInputStream("C:\\Users\\YasserAlejandro\\Documents\\Job Search\\persontest.dat"));
		    pl = (Person) ois.readObject();

		} catch (IOException ex){
			ex.printStackTrace();
		} catch (ClassNotFoundException ex){
			ex.printStackTrace();
		}
		
		pl.printNicknames();
		pl.addNickname("Love you bebe");
		
		
		
		
	}

}
