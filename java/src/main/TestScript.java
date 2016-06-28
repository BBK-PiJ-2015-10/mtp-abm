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
		
		UserSpace user = new UserSpace();
		user.capture("user4");
		
		//System.out.println(user.validConfiguration("config4"));
		
		//System.out.println(user.getConfiguration("config4").getAbsolutePath());
		
		ConfigurationManager config = new ConfigurationManager(user.getConfiguration("config4"));
		config.capture("config4");
		config.getBpaMainFilesAttributesMap().keySet().forEach(System.out::println);
		
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
