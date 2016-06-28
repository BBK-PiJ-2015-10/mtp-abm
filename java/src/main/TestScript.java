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

import configuration.Tuple;



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
		
		/*
		
		String userword;
		
		try (FileWriter fw = new FileWriter(testFile,true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw);
				Scanner sc2 = new Scanner(new FileReader(testFile));
				Scanner keyboard = new Scanner(System.in);
				Scanner sc = new Scanner(new FileReader(test.getGLFile()));)
		{
			//System.out.println("For each o")
			Set<String> validSet= test.getBPAFilesMap().keySet();
			sc2.nextLine();
			System.out.println("Below are the files with the activity driver stored in your configuration: ");
			validSet.forEach(System.out::println);
			System.out.println("For each of the below single or multiple combinations specify the file "
					+ "that contains the activity driver for that combination");
			System.out.println(sc2.nextLine());
			userword = keyboard.nextLine();
			System.out.println("Hello Ale");
			System.out.println("You just entered " +userword);
			
			
			
			//testFile.isDirectory()
			//System.out.println(sc2.hasNext());
			//System.out.println(sc2.next());
			
			//sc.close();
			sc2.close();
			out.close();
			
		} catch (FileNotFoundException ex){
			ex.printStackTrace();
		} catch (IOException ex){
			ex.printStackTrace();
		}
		
		//*/
	
	    ///*
		
		try {
			testFile.createNewFile();
		} catch (IOException ex) {
		   System.out.println("Couldn't create the file");
		}

		try (FileWriter fw = new FileWriter(testFile,false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			Scanner keyboard = new Scanner(System.in);
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
			for (int i=0;i<sentence.length;i++){
				if(attri.contains(sentence[i])){
					attripos.add(i);
				}
			}
			Set<String> attriset = new HashSet<>();
			while (sc.hasNextLine()){
				sentence=sc.nextLine().split(",");
				String bpaDriver;
				String longword = null;
				boolean validEntry = false;
				for (Integer position : attripos){
					try {
						longword.isEmpty();
						longword =longword+" "+sentence[position];
					}	
					catch (NullPointerException ex){
						longword = sentence[position];
					}
				}
				if (!attriset.contains(longword)){
					attriset.add(longword);
					do {
					System.out.println("For: "+longword);
					System.out.println("Type the full name of the file with the driver data for");
					bpaDriver = keyboard.next();
					validEntry = test.getBpaFilesAttributesMap().containsKey(bpaDriver);
					if (!validEntry){
						System.out.println("File named entry doesn't exist");
					}
					} while (!validEntry);
					for (Integer position : attripos){
						out.write(sentence[position]+",");
					}
					out.write(bpaDriver);
				}
				out.println();
			}
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
