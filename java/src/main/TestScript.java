package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import configuration.ConfigurationManager;

import java.util.HashSet;
import java.util.List;


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
		//System.out.println(test.getBPAFileNames());
		
		//System.out.println(test.getFLFile().getName());
		
		//File file = new File(address);
		
	
		Map<String, Set<String>> fieldsMap = new HashMap<>();
		
		
		BufferedReader in = null;
		try {
			File file;
			for (String input: test.getBPAFileNames()){
				file = test.getBPAFile(input);
				//System.out.println(file.getName());
				in = new BufferedReader(new FileReader(file));
				String line;
				line = in.readLine();
				String[] strArray = line.split(",");
				Set<String> fieldsSet = new HashSet<>();
				for (int i=0; i<strArray.length;i++){
					fieldsSet.add(strArray[i]);
				}
				fieldsMap.put(file.getName(), fieldsSet);
			}
			
			Set<String> tester = fieldsMap.keySet();
						
		    tester.forEach(n->{
		    	System.out.println("The file: " +n);
		    	System.out.println("Has the following fields ");
		    	fieldsMap.get(n).forEach(System.out::println);
		    });
			
			//test.getBPAFileNames().forEach(n->test.getBPAFile(n));
			
			
			//File filet;
			//filet = test.getGLFile();
			//in = new BufferedReader(new FileReader(filet));
			//String line;
			//line=in.readLine();
			//System.out.println(line);
			
			//while ((line=in.readLine()) != null){
				//System.out.println(line);
		//	}
			System.out.println("Is this working or not");
			in.close();
		} catch (FileNotFoundException ex){
			System.out.println("File " +test.getGLFile() + " does not exist");
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
