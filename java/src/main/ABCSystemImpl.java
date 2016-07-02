package main;

import java.util.Scanner;

import configuration.ConfigurationMaker;
import configuration.ConfigurationMakerImpl;
import period.PeriodMaker;
import period.PeriodMakerImpl;
import user.UserSpace;
import user.UserSpaceMaker;
import user.UserSpaceMakerImpl;

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
	
	private Scanner sc;
	
	public void runMakeNewUserSpace(){
		userSpaceMaker = new UserSpaceMakerImpl();
		userSpaceMaker.createUserSpace(userSpace,sc);
	}
	
	public void runAccessExistingUserSpace() {
		boolean validName=false;
		System.out.println("Please enter the name of the userspace you wish to access");
		String target = sc.nextLine();
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
		periodMaker.makePeriod();
		periodMaker.save();
	}
	
	
	
	@Override
	public void run() {
		System.out.println("If you are a new user, please enter yes, if not, please enter no");
		String choice = sc.next();
		
		if (choice.equalsIgnoreCase("yes")){
			runMakeNewUserSpace();		
		}
		else {
			runAccessExistingUserSpace();
		}
		
		System.out.println("If you wish to create a new ABC Configuration, please enter yes");
		choice = sc.next();
		if (choice.equalsIgnoreCase("yes")){
			runMakeNewConfiguration();
		}
	
		
		System.out.println("If you wish to run new period, please enter yes");
		choice = sc.next();
		if (choice.equalsIgnoreCase("yes")){
			System.out.println("This is going better");
			runMakeNewPeriod();
			System.out.println("He said yes");
		}
		
		
		

		

		
	}
	
	

}
