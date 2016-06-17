package abc.streamingSamples;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class PersonScript {

	public static void main(String[] args) {
		
		PersonScript script = new PersonScript();
		script.launch();
	}
	
	public void launch(){
		
		Person first = new Person();
		first.setName("Bibbio");
		first.setAge(13);
		first.addNickname("Amorino");
		first.addNickname("Tontolone");
		first.addNickname("Vago");
		
		//Saving object
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\YasserAlejandro\\Documents\\Job Search\\persontest.dat"));
			oos.writeObject(first);
		
		} catch (IOException e){
			e.printStackTrace();
	
		} finally {
			try {
				oos.close();	
			} catch (IOException ex){
				ex.printStackTrace();
			}
		}
		
		
		
		
		
		
		
	}

}
