package main;

import java.util.Scanner;

import bpa.BpaCostsMaker;
import bpa.BpaCostsMakerImplCSV;
import bpa.BpaCostsMakerImplSQL;
import bpa.BpaCostCalculator;
import bpa.BpaCostCalculatorImpl;
import client.BpaClientWeightsCalculator;
import client.BpaClientWeightsCalculatorImpl;
import client.ClientCosts;
import client.ClientCostsImpl;
import configuration.ConfigurationMaker;
import configuration.ConfigurationMakerImplCSV;
import configuration.ConfigurationMakerImplSQL;
import period.PeriodMaker;
import period.PeriodMakerImplCSV;
import period.PeriodMakerImplSQL;
import user.UserSpace;
import user.UserSpaceMaker;
import user.UserSpaceMakerImpl;
import report.ReportGenerator;
import report.ReportGeneratorImpl;



import java.io.File;

public class ABCSystemImplMock implements ABCSystem {

	
	public ABCSystemImplMock(Scanner sc){
		this.sc=sc;
	}
	
	private UserSpace userSpace = new UserSpace();
	
	private UserSpaceMaker userSpaceMaker;	
	
	private ConfigurationMaker configurationMaker;
	
	private PeriodMaker periodMaker;
	
	private BpaCostsMaker bpaCostsMaker;
	
	private BpaCostCalculator bpaCostCalculator;
	
	private BpaClientWeightsCalculator bpaClientWeightsCalculator;
	
	private ClientCosts clientCosts;
	
	private ReportGenerator reportGenerator;
	
	private Scanner sc;
	
	private boolean validEntry=false;
	
	private boolean terminate = false;
	
	private String keyboard = null;
	

	public boolean runMakeNewUserSpace(){
		userSpaceMaker = new UserSpaceMakerImpl();
		userSpaceMaker.createUserSpace(userSpace,sc);
		do {
			System.out.println("Enter yes if you wish to create a new configuration");
			System.out.println("Enter no if you wish to return to the main menu");
			System.out.println("Enter exit if you wish to exit the application");
			keyboard = sc.nextLine();
		} while (!validSelection(keyboard));
		switch (keyboard){
			case "yes":  runMakeNewConfiguration();
			case "no":break;
			case "exit": return true;
		}
		return false;
	}
	
	
	public void runAccessExistingUserSpace() {
		boolean validName=false;
		System.out.println("Please enter the name of the userspace you wish to access");
		String target = sc.nextLine();
		validName = userSpace.capture(target);
		while (!validName){
			System.out.println("Username entered doesn't exist, please re-enter userspace");
			target = sc.nextLine();
			validName = userSpace.capture(target);
		}	
	}
	
	public boolean runMakeNewConfiguration(){
		boolean valid=false;
		String configType = null;
		do
		{
			System.out.println("For a CSV configuration type CSV");
			System.out.println("For a SQL configuration type SQL");
			configType = sc.nextLine().toUpperCase();
			switch (configType){
				case "CSV":  configurationMaker = new ConfigurationMakerImplCSV();
							 valid=true;
							 break;
				case "SQL":  configurationMaker = new ConfigurationMakerImplSQL();
							 valid=true;
							 break;
				default:     System.out.println("Invalid selection");
						     break;
			}
		} while (!valid);
		configurationMaker.makeConfiguration(userSpace,sc,configType);
		do {
			System.out.println("Enter yes if you wish to create a new period");
			System.out.println("Enter no if you wish to return to the main menu");
			System.out.println("Enter exit if you wish to exit the application");
			keyboard = sc.nextLine();
		} while (!validSelection(keyboard));
		switch (keyboard){
			case "yes":  runMakeNewPeriod();
			break;
			case "no":break;
			case "exit": return true;
		}
		return false;
	}
	
	
	public boolean accessExistingPeriod(){
		boolean result = false;
		String periodName;
		do {
			System.out.println("Please enter the name of the period you wish to access");
			periodName = sc.nextLine();
			if(userSpace.validPeriod(periodName)){
				boolean valid=false;
				do {
					String configType;
					configType = userSpace.getPeriodConfigurationType(periodName);
					switch (configType.toUpperCase()){
						case "CSV":  periodMaker = new PeriodMakerImplCSV(new File(userSpace.getPeriod(periodName).getAbsolutePath()));
							valid=true;	
							break;
						case "SQL":  periodMaker = new PeriodMakerImplSQL(new File(userSpace.getPeriod(periodName).getAbsolutePath()));
					 		valid=true;	
							break;
						default:     
							System.out.println("Invalid selection");
							break;
					}
				} while (!valid);
				periodMaker.capture(periodName);
				result = true;
			}
			else {
				System.out.println("Invalid selection");
			}
		} while (!userSpace.validPeriod(periodName));
		return result;
	}
	
	
	public String createPeriodMaker(){
		String result=null;
		String configName;
		do {
			System.out.println("Please enter the name of the configuration to use");
			configName = sc.nextLine();
			if (!userSpace.validConfiguration(configName)){
				System.out.println("Invalid configuration name");
			}
		} while(!userSpace.validConfiguration(configName));
		String configType = userSpace.getConfigurationType(configName);
		switch (configType.toUpperCase()){
			case "CSV":  periodMaker = new PeriodMakerImplCSV(userSpace);
						 result="CSV";
						 break;
			case "SQL":  periodMaker = new PeriodMakerImplSQL(userSpace);
						 result="SQL";
						 break;
			default:     System.out.println("Invalid selection");
					     break;
		}
		periodMaker.makePeriod(sc,configName);
		periodMaker.save();
		return result;	
	}
	
	
	public boolean runMakeNewPeriod(){
		runBpaCostMaker(createPeriodMaker(),sc);
		bpaCostCalculator = new BpaCostCalculatorImpl(periodMaker.getBpaCosts());
		bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(bpaCostsMaker);
		clientCosts = new ClientCostsImpl(bpaCostCalculator,bpaClientWeightsCalculator);
		clientCosts.calculateClientCosts();
		do {
			System.out.println("Enter yes if you wish to run a report(s)");
			System.out.println("Enter no if you wish to return to the main menu");
			System.out.println("Enter exit if you wish to exit the application");
			keyboard = sc.nextLine();
		} while (!validSelection(keyboard));
		switch (keyboard){
			case "yes":  runGenerateReport();
					     break;
			case "no":   break;
			case "exit": return true;
		}
		return false;
	}
	
	
	
	public boolean runBpaCostMaker(String periodType, Scanner sc){
		switch (periodType.toUpperCase()){
			case "CSV":  bpaCostsMaker = new BpaCostsMakerImplCSV(periodMaker);
						 break;
			case "SQL":  bpaCostsMaker = new BpaCostsMakerImplSQL(periodMaker);
			 			 break;
			}
		return bpaCostsMaker.createbpaCosts(sc);
	}
	
	
	public boolean runGenerateReport(){
		reportGenerator= new ReportGeneratorImpl(periodMaker);
		reportGenerator.captureChoice(sc);
		reportGenerator.generateReport();
		do {
			System.out.println("Enter yes if you wish to run another report(s)");
			System.out.println("Enter no if you wish to return to the main menu");
			System.out.println("Enter exit if you wish to exit the application");
			keyboard = sc.nextLine();
		} while (!validSelection(keyboard));
		switch (keyboard){
			case "yes":  return runGenerateReport();
			case "no":   break;
			case "exit": return true;
		}
		return false;
	}
	
	
	public boolean validSelection(String selection){
		switch (selection.toLowerCase()){
			case "yes" : return true;
			case "no"  : return true;
			case "exit": return true; 
		}
		return false;
	}
	
	
	public boolean validMainSelection(String selection){
		switch (selection.toLowerCase()){
			case "user" : return true;
			case "configuration"  : return true;
			case "period" : return true;
			case "report" :return true;
			case "exit": return true;
		}
		return false;
	}
	
	
	public boolean launchABCServiceRequest(String selection){
		switch (selection.toLowerCase()){
			case "user" : 
				return runMakeNewUserSpace();
			case "configuration"  : 
				runAccessExistingUserSpace();
				return runMakeNewConfiguration();
			case "period" : 
				runAccessExistingUserSpace();
				return runMakeNewPeriod();
			case "report" :
				runAccessExistingUserSpace();
				accessExistingPeriod();
				return runGenerateReport();
			case "exit" :
				return true;
		}
		return false;
	}
	
	
	@Override
	public void run() {
		
		System.out.println("Welcome to the Activity Based Costing Application");
		String choice;
		terminate = false;
		
		do {
			
			do {
				System.out.println();
				System.out.println("To create a new user type: user");
				System.out.println("To create a new configuration type: configuration");
				System.out.println("To create a new period type: period");
				System.out.println("To run a report type: report");
				System.out.println("To exit the application type: exit");
				choice = sc.nextLine();
				if (!(validEntry = validMainSelection(choice))){
					System.out.println("Invalid selection, please select again");
				}
			} while (!validEntry);
				
			terminate=launchABCServiceRequest(choice);
		
		} while (!terminate);
		
		System.out.println("Good bye user. Thank you for choosing Activity Based Costing Application");
			
	}
	
	

	
}
