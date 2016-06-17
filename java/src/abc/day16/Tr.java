package abc.day16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tr {

	public static void main(String[] args) {
		System.out.println("This is the Tr file");
		if (args.length != 3){
			System.out.println("Invalid args");
		}
		Tr tr = new Tr();
		tr.launch(args[0],args[1],args[2]);
	}
	
	public void launch(String fileName, String first, String second){
		File file = new File(fileName);
		try (BufferedReader in = new BufferedReader(new FileReader(file))){
			
			String line;
			while ((line=in.readLine()) != null) {
				System.out.println(line.replace(first, second));
			}
			
		} catch (FileNotFoundException ex){
			System.out.println("File " + file + " does not exist");
		} catch (IOException ex){
			ex.printStackTrace();
		}
		
		
	}

}
