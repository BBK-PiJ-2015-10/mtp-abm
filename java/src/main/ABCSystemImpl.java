package main;

import java.util.Scanner;

import bpa.BpaCostsMaker;
import bpa.BpaCostsMakerImpl;
import bpa.BpaClientWeightsCalculator;
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
	
	public void runMakeNewUserSpace(){
		userSpaceMaker = new UserSpaceMakerImpl();
		userSpaceMaker.createUserSpace(userSpace,sc);
	}
	
	public void runAccessExistingUserSpace() {
		boolean validName=false;
		System.out.println("Please enter the name of the userspace you wish to access");
		String target = sc.next();
		validName = userSpace.capture(target);
		while (!validName){
			System.out.println("Username entered doesn't exist, please re-enter userspace");
			target = sc.next();
			validName = userSpace.capture(target);
		}	
	}
	
	public void runMakeNewConfiguration(){
		configurationMaker = new ConfigurationMakerImpl();
		configurationMaker.makeConfiguration(userSpace,sc);
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
		reportGenerator= new ReportGeneratorImpl();
		reportGenerator.captureChoice(sc);
		//return reportGenerator.generateReport(srcFile, report)
		return true;
	}
	
	
	
	@Override
	public void run() {
		System.out.println("If you are a new user, please enter yes, if not, please enter no");
		String choice = sc.nextLine();
		
		if (choice.equalsIgnoreCase("yes")){
			runMakeNewUserSpace();		
		}
		else {
			runAccessExistingUserSpace();
		}
		
		System.out.println("If you wish to create a new ABC Configuration, please enter yes");
		choice = sc.nextLine();
		if (choice.equalsIgnoreCase("yes")){
			runMakeNewConfiguration();
		}
	
		
		System.out.println("If you wish to run new period, please enter yes");
		choice = sc.nextLine();
		if (choice.equalsIgnoreCase("yes")){
			System.out.println("This is going better");
			runMakeNewPeriod();
			runBpaCostMaker();
			bpaCostCalculator = new BpaCostCalculatorImpl(periodMaker.getBpaCosts());
			bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(bpaCostsMaker);
			clientCosts = new ClientCostsImpl(bpaCostCalculator,bpaClientWeightsCalculator);
			clientCosts.calculateClientCosts();
			System.out.println("You are done for the moment");
		}
		
		
		System.out.println("Do you wish to generate a report");
		choice = sc.nextLine();
		if (choice.equalsIgnoreCase("yes")){
			
		}

		

		
	}
	
	

}
