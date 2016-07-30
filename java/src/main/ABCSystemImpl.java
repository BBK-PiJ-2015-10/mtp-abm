package main;

import java.util.Scanner;

import bpa.BpaCostsMaker;
import bpa.BpaCostsMakerImpl;
import bpa.BpaClientWeightsCalculator;
import bpa.BpaClientWeightsCalculatorImplOld;
import bpa.BpaClientWeightsCalculatorImpl;
import bpa.BpaCostCalculator;
import bpa.BpaCostCalculatorImpl;
import client.ClientCosts;
import client.ClientCostsImpl;
import configuration.ConfigurationMaker;
import configuration.ConfigurationMakerImpl;
import period.PeriodMaker;
import period.PeriodMakerImpl;
import user.UserSpace;
import user.UserSpaceMaker;
import user.UserSpaceMakerImpl;
import report.ReportAbstract;
import report.ReportGenerator;
import report.ReportGeneratorImpl;

import java.io.File;

public class ABCSystemImpl implements ABCSystem {

	//Will do dependency injection latter. This might go away.
	
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
		configurationMaker = new ConfigurationMakerImpl();
		configurationMaker.makeConfiguration(userSpace,sc);
	}
	
	
	public boolean accessExistingPeriod(){
		boolean result = false;
		System.out.println("Please enter the name of the period you wish to access");
		String periodName = sc.nextLine();
		if(userSpace.validPeriod(periodName)){
			periodMaker = new PeriodMakerImpl(new File(userSpace.getPeriod(periodName).getAbsolutePath()));
			periodMaker.capture(periodName);
			result = true;
		}
		return result;
	}
	
	public void runMakeNewPeriod(){
		periodMaker = new PeriodMakerImpl(userSpace);
		periodMaker.makePeriod(sc);
		periodMaker.save();
	}
	
	public boolean runBpaCostMaker(){
		bpaCostsMaker = new BpaCostsMakerImpl(periodMaker);
		return bpaCostsMaker.createbpaCosts();
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
		
		
		System.out.println("If you wish to create a new ABC Configuration, please enter yes");
		choice = sc.nextLine();
		if (choice.equalsIgnoreCase("yes")){
			runMakeNewConfiguration();
		}
	
		
		//validEntry=false;
		do {
			System.out.println("Enter yes if you wish to create a new period, enter no if you wish "
					+ "to access an existing period");
			choice = sc.nextLine();
			validEntry = validSelection(choice);
			if (validEntry){
				if (choice.equalsIgnoreCase("yes")){
					runMakeNewPeriod();
					runBpaCostMaker();
					bpaCostCalculator = new BpaCostCalculatorImpl(periodMaker.getBpaCosts());
					//bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(bpaCostsMaker);
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
