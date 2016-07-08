package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Arrays;

//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;

//import configuration.ConfigurationManager;
//import user.UserSpace;

//import period.PeriodMaker;
//import period.PeriodMakerImpl;

//import java.util.HashSet;
//import java.util.List;
//import java.util.LinkedList;

//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.PrintWriter;
import java.util.Scanner;

import period.PeriodMaker;
import period.PeriodMakerImpl;

import bpa.BpaCostsMaker;
import bpa.BpaCostsMakerImpl;



public class TestScript {

	public static void main(String[] args) {
		
		TestScript script = new TestScript();
		script.launch();

	}
	
	public void launch(){
		
		String testaddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user14\\period14";
		File testFile = new File(testaddress);
	    PeriodMaker testPeriod = new PeriodMakerImpl(testFile);
	    testPeriod.capture("period14");
	    
		BpaCostsMaker test = new BpaCostsMakerImpl(testPeriod);
		test.createbpaCosts();
		
		//Ask user to place list of files on period folder.
		//The below will give me access to the GLFile
		//test.getPeriodMaker().getConfiguration().getGLFile();
		/*
		 * Copy all rows from the Gl file, but only the relevant columns
		 * Do a vlookup of all the columns being copied vs the mapping file to get the BPA driver
		 * Don't forget the amounts
		 * Need to figure out how to detect not mapped yet combinations either on the fly or before.
		 * Try first not to validate, then run validation.
		 */
		//test.getPeriodMaker().getConfiguration().getBpaFilesAttributesMap().keySet().forEach(System.out::println);
		
		
		//System.out.println("Back on programming mode");
		
	    //ByteArrayInputStream auto = new ByteArrayInputStream("Ale Cane".getBytes());
	    //System.setIn(auto);
	    
	    //Scanner tester = new Scanner(System.in);
	    
	    //String value;
	    
	    //value=tester.nextLine();
	    
	    //System.out.println("I have entered: " +value);
	    
		
		//test.
		
		//System.setIn("ALE");
		
		//System.out.println("I am reading from screen old fashioned");
		//String key = System.console().readLine();
		//System.out.println("You have entered : "+keyboard);
		
		
		
		///*
		//String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user4\\period4";
		
		//File periodF = new File(address);
		
		//PeriodMakerImpl test  = new PeriodMakerImpl(periodF);
		//test.capture("period4");
		//System.out.println(test.getConfiguration().getBpaFilesAttributesMap().size());
		
		//periodF.
		
		//System.out.println(periodF.exists());
		
		//*/
		
		/*
		UserSpace user = new UserSpace();
		user.capture("user4");	
		ConfigurationManager config = new ConfigurationManager(user.getConfiguration("config4"));
		config.capture("config4");
		
		PeriodMakerImpl period = new PeriodMakerImpl(user);
		period.makePeriod();
		period.save();
		
		
		System.out.println("You did well");
		
		/*
		
		//config.getBpaMainFilesAttributesMap().keySet().forEach(System.out::println);
		
		//config.getBpaFilesAttributesMap().keySet().forEach(System.out::println);
		
		//trust = user.validConfiguration("config4");
		
		
		/*
		
		String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user3\\config3";
		
		File config = new File(address);
		ConfigurationManager test = new ConfigurationManager(config);
		test.capture("config3");
				
		File testFile = new File(test.getFile().getAbsolutePath()+"\\"+"glbpamap.csv");
		
		*/
		
		
	



		
	}
	
	
		
}
