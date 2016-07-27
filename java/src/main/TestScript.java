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

import configuration.ConfigurationManager;
import configuration.MapCreatorImpl;

import period.PeriodMaker;
import period.PeriodMakerImpl;
import systemTesting.userTesting.TestBpaCostsMakerImpl;
import user.UserSpace;
import bpa.BpaCostCalculator;
import bpa.BpaCostCalculatorImpl;
import bpa.BpaCostsMaker;
import bpa.BpaCostsMakerImpl;
import bpa.BpaClientWeightsCalculator;
import bpa.BpaClientWeightsCalculatorImpl;

import report.ReportGenerator;
import report.ReportGeneratorImpl;
import report.ReportSummaryImpl;
import report.ReportGenerator;
import report.ReportDetailedImpl;

import client.ClientCosts;
import client.ClientCostsImpl;

import report.ReportAbstract;

import java.util.regex.Pattern;

import java.util.List;
import java.util.LinkedList;


public class TestScript {

	public static void main(String[] args) {
		
		TestScript script = new TestScript();
		script.launch();

	}
	
	public void launch(){
		
		Scanner sc = new Scanner(System.in);
		
		String cmAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user19\\config19";
		File cmFile = new File(cmAddress);
		ConfigurationManager testCM = new ConfigurationManager(cmFile);
		System.out.println(testCM.capture("config19"));
		
		//MapCreatorImplManual testMC = new MapCreatorImplManual();
		//System.out.println(testMC.createMap(testCM,sc,"testMap"));
		//System.out.println("What happened");
		
		//String periodFileAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16";
		//File periodFile = new File(periodFileAddress);
		//PeriodMaker testPeriod = new PeriodMakerImpl(periodFile);
		//testPeriod.capture("period16");
		
		//ReportDetailedImpl reportDetailedImpl = new ReportDetailedImpl();
	    //System.out.println(reportDetailedImpl.generateReport(testPeriod.getBpaCosts(), "DetailedBPA","BPA","Amount"));
		
		
	    //ReportAbstract abstractReport=null;
	    
		//ReportGenerator reportGenerator = new ReportGeneratorImpl(testPeriod);
		//reportGenerator.captureChoice(sc);
		//reportGenerator.generateReport();
		
		
		
		
		//System.out.println(testPeriod.getBpaCosts().getAbsolutePath());
	

		//String bpaFileAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\bpaCosts.csv";
		//String bpaFileAddress;
		//bpaFileAddress = testPeriod.getBpaCosts().getAbsolutePath();
		//File testBpaFile = new File(bpaFileAddress);
		
		//ReportDetailedImpl reportDetailedImpl = new ReportDetailedImpl();
	    //System.out.println(reportDetailedImpl.generateReport(testPeriod.getBpaCosts(), "DetailedBPA","BPA","Amount"));
	    
	    
		
		//String bpaFileAddress2 = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\bpaCosts2.csv";
		//File testBpaFile2 = new File(bpaFileAddress2);
		
		
		//String clientFileAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\clientCosts.csv";
		//File testClientFile = new File(clientFileAddress);
		
		//String clientFileAddress2 = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\clientCosts2.csv";
		//File testClientFile2 = new File(clientFileAddress2);
		
		
		//String clientFileAddress3 = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\clientCosts3.csv";
		//File testClientFile3 = new File(clientFileAddress3);
		
		
		//ReportGenerator reportGenerator = new ReportGeneratorImpl();
		//reportGenerator.presentChoices();
		//reportGenerator.captureChoice(sc);
		
				
		//ReportAbstract abstractReport=null;
		
		
		
		//System.out.println(ReportGenerator.generateReport(testBpaFile,3,abstractReport));
		//System.out.println(ReportGenerator.generateReport(testClientFile,,abstractReport));
		
		
		
		//System.out.println(ReportGenerator.generateReport(testBpaFile,3,abstractReport));
		//System.out.println(ReportGenerator.generateReport(testBpaFile,4,abstractReport));
		//System.out.println(ReportGenerator.generateReport(testBpaFile2,5,abstractReport));
		
		
		//System.out.println(ReportGenerator.generateReport(testClientFile,1,abstractReport));		
		//System.out.println(ReportGenerator.generateReport(testClientFile,2,abstractReport));
		//System.out.println(ReportGenerator.generateReport(testBpaFile,3,abstractReport));
		//System.out.println(ReportGenerator.generateReport(testBpaFile,6,abstractReport));
		//System.out.println(ReportGenerator.generateReport(testClientFile,4,abstractReport));
		//System.out.println(ReportGenerator.generateReport(testClientFile,5,abstractReport));
			
	    //ReportDetailedImpl reportDetailedImpl = new ReportDetailedImpl();
	    //reportDetailedImpl.createFile(testBpaFile, "testerDetailedClient");
	    //System.out.println(reportDetailedImpl.popMap(testBpaFile,"BPA","Amount"));
	
		//System.out.println(reportDetailedImpl.createFile(testBpaFile, "testerDetailedClient"));
		//System.out.println(reportDetailedImpl.popMap(testBpaFile,"BPA","Amount"));
		
		//reportDetailedImpl.getAttributesLabels().forEach(System.out::println);
		//reportDetailedImpl.getClientsCosts().keySet().forEach(System.out::println);
		//reportDetailedImpl.getClientsCosts().get("implementation").forEach(System.out::println);
		
		//ReportAbstract abstractReport=null;
		//System.out.println(ReportGenerator.generateReport(testBpaFile,1,abstractReport));
		//System.out.println(ReportGenerator.generateReport(testBpaFile,2,abstractReport));
	    //System.out.println(ReportGenerator.generateReport(testBpaFile,3,abstractReport));
		
		
		
		//String clientFileAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\clientCosts.csv";
		//File testClientFile = new File(clientFileAddress);
		
		
		//ReportAbstract abstractReport=null;
		
		
		
		//ReportGenerator.generateReport(testClientFile,1,abstractReport);
		//ReportGenerator.generateReport(testClientFile,2,abstractReport);
		//ReportGenerator.generateReport(testClientFile,3,abstractReport);
		
		//Report test = new ReportSummaryImpl();
		//System.out.println(test.generateReport(testClientFile,1));
		
		//System.out.println(test.generateReport(testClientFile,2));
		
		//ReportSummaryImpl test2 = new ReportSummaryImpl();
		//System.out.println(test2.generateReport(testClientFile,"testorclient","BPA","cost"));
		
		//System.out.println(test2.generateReport(testClientFile,"testorbpa","client","cost"));
		
		
	
		//Scanner sc = new Scanner(System.in);
		
		
		//String testaddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user15\\period15";
		//File testFile = new File(testaddress);
		//PeriodMaker testPeriod = new PeriodMakerImpl(testFile);
		//testPeriod.capture("period15");
		
		//BpaCostsMaker testCostMaker = new BpaCostsMakerImpl(testPeriod);
		//testCostMaker.createbpaCosts();
		//BpaClientWeightsCalculator  bpaClientCalculator = new BpaClientWeightsCalculatorImpl(testCostMaker);
		
		//BpaCostCalculator bpaCostCalculator = new BpaCostCalculatorImpl(testPeriod.getBpaCosts());
		
		//ClientCosts clientCosts = new ClientCostsImpl(bpaCostCalculator,bpaClientCalculator);
		//clientCosts.calculateClientCosts();
		
		
		
		
		
		//bpaClientCalculator.getClientsWeights().keySet().forEach(System.out::println);
		
		
		
		
		
		//testCostMaker.createBpaCostsFile();
		
		
		
		//BpaCostCalculator bpaCostCalculator = new BpaCostCalculatorImpl(testPeriod.getBpaCosts());
		//System.out.println(bpaCostCalculator.getActivityCost("phones.csv"));
		//System.out.println(bpaCostCalculator.getActivityCost("implementation.csv"));
		//String testaddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user13";
		//File testFile = new File(testaddress);
		//UserSpace testUser = new UserSpace();
		//testUser.FileSetUserSpaceFile(testFile);
		//testUser.capture("user13");
		//PeriodMaker testPeriod = new PeriodMakerImpl(testUser);
		//testPeriod.makePeriod(sc);
		//testPeriod.save();
		
		
		//PeriodMaker testPeriod = new PeriodMakerImpl(testFile);
		//testPeriod.capture("period15");
		
		//BpaCostsMaker testCostMaker = new BpaCostsMakerImpl(testPeriod);
		//testCostMaker.createBpaCostsFile();
	
		
		//BpaCostCalculator bpaCostCalculator = new BpaCostCalculatorImpl(testCostMaker);
		
		//BpaClientWeightsCalculator  bpaClientCalculator = new BpaClientWeightsCalculatorImpl(testCostMaker);
		//bpaClientCalculator.getClientsWeights();
		
		//ClientCosts clientCosts = new ClientCostsImpl(bpaCostCalculator,bpaClientCalculator);
		//clientCosts.calculateClientCosts();
		
		//bpaClientCalculator.displayMap();
		//bpaClientCalculator.test();
		
		
		
		//((BpaCostCalculator)testCostMaker).getActivityCost("ale");
		
		//testCostMaker
		
		//ClientCostsImpl clientcost = new ClientCostsImpl(testCostMaker);
		//System.out.println(clientcost.getActivityCost("phones.csv"));
		
		//System.out.println(testCostMaker.getBPACosts().exists());
		
		//System.out.println(testFile.exists());
		//UserSpace testUser = new UserSpace();
		//testUser.FileSetUserSpaceFile(testFile);
		//testUser.capture("user17");
		//PeriodMaker testPeriod = new PeriodMakerImpl(testUser);
		//testPeriod.makePeriod(sc);
		
		
		
		//testUser.getConfigurationsNames().forEach(System.out::println);
		
		//System.out.println(testPeriod.getPeriod().getAbsolutePath());
		
		
		
		//testPeriod.getPeriodFiles().keySet().forEach(System.out::println);
		//System.out.println("Now the next");
		//testPeriod.getDriversMap().keySet().forEach(System.out::println);
	
		//testPeriod.getConfiguration().getBpaMainFilesAttributesMap().keySet().forEach(n->System.out.println(n));
		//testPeriod.getConfiguration().getBpaMainFilesAttributesMap().get("phones.csv").forEach(System.out::println);
		
		
		//testPeriod.getConfiguration().getBpaFilesAttributesMap().keySet().forEach(System.out::println);
		//testPeriod.makePeriod(sc);
		//testPeriod.save();
		
		
		
		
		//System.out.println("This is the end");
		//test.capture(directoryname)
		
		//*/
		
		
		//String testaddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user17";
		//File testFile = new File(testaddress);
	    //PeriodMaker testPeriod = new PeriodMakerImpl(testFile);
	    //testPeriod.makePeriod(sc);
	    //testPeriod.capture("period17");
	    
		//BpaCostsMaker test = new BpaCostsMakerImpl(testPeriod);
		//test.createbpaCosts();
		
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
