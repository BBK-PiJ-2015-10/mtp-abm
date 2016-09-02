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
import old.ConfigurationMakerImpl;
import period.PeriodMaker;
import period.PeriodMakerImplCSV;
import period.PeriodMakerImplSQL;
import user.UserSpace;
import user.UserSpaceMaker;
import user.UserSpaceMakerImpl;
import report.ReportAbstract;
import report.ReportGenerator;
import report.ReportGeneratorImpl;

import sqlimpl.*;

import java.io.File;

public class ABCSystemImpl implements ABCSystem {

	
	public ABCSystemImpl(Scanner sc){
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
	
	public boolean runMakeNewUserSpace(){
		userSpaceMaker = new UserSpaceMakerImpl();
		userSpaceMaker.createUserSpace(userSpace,sc);
		return true;
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
	
	public void runMakeNewConfiguration(){
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
	}
	
	
	public boolean accessExistingPeriod(){
		boolean result = false;
		System.out.println("Please enter the name of the period you wish to access");
		String periodName = sc.nextLine();
		if(userSpace.validPeriod(periodName)){
			boolean valid=false;
			do
			{
				String configType;
				configType = userSpace.getPeriodConfigurationType(periodName);
				switch (configType.toUpperCase()){
					case "CSV":  periodMaker = new PeriodMakerImplCSV(new File(userSpace.getPeriod(periodName).getAbsolutePath()));
								 valid=true;	
								 break;
					case "SQL":  periodMaker = new PeriodMakerImplSQL(new File(userSpace.getPeriod(periodName).getAbsolutePath()));
					 			 valid=true;	
								 break;
					default:     System.out.println("Invalid selection");
							     break;
				}
			} while (!valid);
			periodMaker.capture(periodName);
			result = true;
		}
		return result;
	}
	
	
	public String runMakeNewPeriod(){
		String result=null;
		boolean valid=false;
		do
		{
			System.out.println("If the configuration to use is a CSV implementation, type CSV");
			System.out.println("If the configuration to use is a SQL implementation, type SQL");
			String configType = sc.nextLine();
			switch (configType.toUpperCase()){
				case "CSV":  periodMaker = new PeriodMakerImplCSV(userSpace);
							 valid=true;
							 result="CSV";
							 break;
				case "SQL":  periodMaker = new PeriodMakerImplSQL(userSpace);
							 valid=true;
							 result="SQL";
							 break;
				default:     System.out.println("Invalid selection");
						     break;
			}
		} while (!valid);
		periodMaker.makePeriod(sc);
		periodMaker.save();
		return result;
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
		return reportGenerator.generateReport();
	}
	
	public boolean validSelection(String selection){
		switch (selection.toLowerCase()){
			case "yes" : return true;
			case "no"  : return true;
		}
		return false;
	}
	
	
	
	@Override
	public void run() {
		
		String choice;
		do {
			System.out.println("If you are a new user, please enter yes, if not, please enter no");
			choice = sc.nextLine();
			validEntry = validSelection(choice);
			if (validEntry){
				if (choice.equalsIgnoreCase("yes")){
					runMakeNewUserSpace();		
				}
				else {
					runAccessExistingUserSpace();
				}
			}	
		} while (!validEntry);
		
		do {
			System.out.println("If you wish to create a new ABC Configuration, please enter yes");
			choice = sc.nextLine();
			validEntry = validSelection(choice);
			if (validEntry){
				if (choice.equalsIgnoreCase("yes")){
					runMakeNewConfiguration();		
				}
			}	
		} while (!validEntry);
		
		do {
			System.out.println("Enter yes if you wish to create a new period, enter no if you wish "
					+ "to access an existing period");
			choice = sc.nextLine();
			validEntry = validSelection(choice);
			if (validEntry){
				if (choice.equalsIgnoreCase("yes")){
					runBpaCostMaker(runMakeNewPeriod(),sc);
					bpaCostCalculator = new BpaCostCalculatorImpl(periodMaker.getBpaCosts());
					bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(bpaCostsMaker);
					clientCosts = new ClientCostsImpl(bpaCostCalculator,bpaClientWeightsCalculator);
					clientCosts.calculateClientCosts();
				} else {
					do {
						validEntry = accessExistingPeriod();
						if (!validEntry){
							System.out.println("Invalid entry");
						}				
					} while (!validEntry);	
				}
			}
		} while (!validEntry);
		
		
		do {
			System.out.println("Enter yes if you wish to run a report, enter no if not");
			choice = sc.nextLine();
			validEntry = validSelection(choice);
			if (choice.equalsIgnoreCase("yes")){
				runGenerateReport();
				do {
					System.out.println("Enter yes if you wish to run another report, enter no if not");
					choice = sc.nextLine();
					validEntry = validSelection(choice);
					if (validEntry){
						if (choice.equalsIgnoreCase("yes")){
							runGenerateReport();
							validEntry=false;
						}
						else {
							validEntry=true;
						}
					}
				} while (!validEntry);	
			}
		} while (!validEntry);
		
		
		
		
		
	}
	
	

}
