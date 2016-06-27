package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import java.util.LinkedList;

import java.util.Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;




public class TestScript {

	public static void main(String[] args) {
		
		TestScript script = new TestScript();
		script.launch();

	}
	
	public void launch(){
		
		
		String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user3\\config3";
		
		File config = new File(address);
		ConfigurationManager test = new ConfigurationManager(config);
		test.capture("config3");
				
		File testFile = new File(test.getFile().getAbsolutePath()+"\\"+"glbpamap.csv");
		
	    ///*
		
		try {
			testFile.createNewFile();
		} catch (IOException ex) {
		   System.out.println("Couldn't create the file");
		}

		try (FileWriter fw = new FileWriter(testFile,false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);	
			Scanner sc = new Scanner(new FileReader(test.getGLFile()));)
		{
			int size = test.getGlMainFilesAttributesMap().get(test.getGLFile().getName()).size();
			List<String> attri =test.getGlMainFilesAttributesMap().get(test.getGLFile().getName()).subList(0,size-1);
			List<Integer> attripos = new LinkedList<>();
			for (int i=0;i<attri.size();i++){
				out.write(attri.get(i)+",");
			}
			out.write("BPA");
			out.println();
			String line;
			String[] sentence;
			sentence=sc.nextLine().split(",");
			System.out.println(sentence.length);
			for (int i=0;i<sentence.length;i++){
				if(attri.contains(sentence[i])){
					attripos.add(i);
				}
			}
			while (sc.hasNextLine()){
				sentence=sc.nextLine().split(",");
				String word;
				for (Integer position : attripos){
					word = sentence[position];
					out.write(word+",");
				}
				out.println();
			}
			//System.out.println(linesc.next());
			//while (sc.hasNextLine()){
				//line=sc.nextLine();
				//System.out.println(line);
			//}
			
			
			
			sc.close();
			out.close();
		} catch (FileNotFoundException ex){
			ex.printStackTrace();
		} catch (IOException ex){
			ex.printStackTrace();
		}
		
		//*/
		
		
	



		
	}
	
	
		
}
