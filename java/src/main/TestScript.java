package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public class TestScript {

	public static void main(String[] args) {
		
		TestScript script = new TestScript();
		script.launch();

	}
	
	public void launch(){
		
		String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user1\\config1";
		
		File config = new File(address);		
		
		ConfigurationManager test = new ConfigurationManager(config);
		test.capture("config1");
		System.out.println(test.getFileNames());
		
		System.out.println(test.getFLFile().getName());
		
		//File file = new File(address);
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(test.getFLFile()));
			String line;
			while ((line=in.readLine()) != null){
				System.out.println(line);
			}
			System.out.println("Is this working");
			in.close();
		} catch (FileNotFoundException ex){
			System.out.println("File " +test.getFLFile() + " does not exist");
		} catch (IOException ex){
			ex.printStackTrace();
		} finally {
			closeReader(in);
		}
		
	}

	private void closeReader(Reader reader){
		try {
			if (reader != null){
				reader.close();
			}
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	
}
