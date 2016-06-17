package main;

import java.util.Scanner;
import java.io.File;

public class ABCSystemImpl implements ABCSystem {

	//Will do dependency injection latter. This might go away.
	
	private UserSpace userSpace = new UserSpace();
	
	private UserSpaceMaker userSpaceMaker;	
	
	private ConfigurationMaker configurationMaker;
	
	@Override
	public void run() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("If you are a new user, please enter yes, if not, please enter no");
		String choice = sc.nextLine();
		
		if (choice.equals("yes")){
			userSpaceMaker = new UserSpaceMakerImpl();
			userSpaceMaker.createUserSpace(userSpace);
			configurationMaker = new ConfigurationMakerImpl();
			configurationMaker.makeConfiguration(userSpace);		
		}
		if (choice.equals("no")){
			System.out.println("Please enter the name of the userspace you with to access");
			String target = sc.nextLine();
			String address = "C:\\Users\\YasserAlejandro\\mtp\\mtp-abm\\";
			File aFile = new File(address+target);
			System.out.println(aFile.exists());
			System.out.println(aFile.getName());
			
		}
		
		System.out.println("Please go to the below location and drop your general ledger and operation data files");
		
		
		
		//else {
			//System.out.println("Please enter the name of the configuration you wish to access");
			//String 
			
		//}
		
		
		System.out.println("This is under development");
		
		//configurationMaker.makeConfiguration();
		
	}
	
	

}
