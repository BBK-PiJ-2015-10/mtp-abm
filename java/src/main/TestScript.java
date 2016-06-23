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
import user.UserSpace;

import java.util.HashSet;
import java.util.List;





public class TestScript {

	public static void main(String[] args) {
		
		TestScript script = new TestScript();
		script.launch();

	}
	
	public void launch(){
		

		
		/*
		String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user2\\config2\\config2.dat";
		File testf= new File(address);
		ConfigurationManager tester = new ConfigurationManager(testf);
		System.out.println(tester.getBPAFileNames());
		tester.capture("config2");
		System.out.println(tester.getBPAFileNames());
		/*
		
		//System.out.println(test2.exists());
		//UserSpace first = new UserSpace();
		//first.FileSetUserSpaceFile(test2);
		//System.out.println(first.getUserSpaceFile().getAbsolutePath());
		//first.save();
		
		
		
		//String address1 = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test";
		//File test1 = new File(address1);
		//System.out.println(test1.getAbsolutePath());
		
		*/
		
		
	
		//String sentence = "Yasser.Palacios";
		//System.out.println(sentence.split(" ").length);
		
	
		
		String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user2\\config2";
		
		File config = new File(address);
		//System.out.println(config.getName());
		
		ConfigurationManager test = new ConfigurationManager(config);
		//test.capture(configurationname)
		
		//System.out.println(test.getFile().getName());
		
		//test.capture("config2");
		//System.out.println(test.getFile().getName());
		
		//System.out.println(test.getBPAFileNames());
		//Set<String> inputset = test.getBpaFilesAttributesMap().keySet();
		//System.out.println(inputset);
		//System.out.println(test.getBpaFilesAttributesMap().size());
		//for (String inputstring: inputset){
			//test.getBpaFilesAttributesMap().get(inputstring).forEach(System.out::println);	
		//}
		//System.out.println("Is this over");
		
		//test.loadGlFilesMainAttributes();
		//System.out.println(test.getFLFile().getName());
		
		//File file = new File(address);
		
		
		/*
	
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
	
	*/
		
	}
	
	
		
}
