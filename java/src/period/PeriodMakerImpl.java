package period;

import user.UserSpace;

import java.io.File;
import java.util.Scanner;

import configuration.ConfigurationManager;

public class PeriodMakerImpl implements PeriodMaker {
	
	private UserSpace userSpace;
	
	private ConfigurationManager configurationManager;
		
	public PeriodMakerImpl(UserSpace userSpace){
		this.userSpace = userSpace;
	}
	
	public boolean validateConfiguration(String configName){
		return userSpace.validConfiguration(configName);
	}
	
	public void captureConfiguration(){
		Scanner sc = new Scanner(System.in);
		Boolean validEntry = false;
		String keyboardEntry;
		do {
			System.out.println("Please enter the name of the configuration that you wish to leverage");
			keyboardEntry = sc.next();
			validEntry = validateConfiguration(keyboardEntry);
			if (!validEntry){
				System.out.println("Configuration name not found");
			}
		} while (!validEntry);
		configurationManager = new ConfigurationManager(userSpace.getConfiguration(keyboardEntry));
		configurationManager.capture(keyboardEntry);		
	}
	
	public void createPeriod(){
		Scanner sc = new Scanner(System.in);
		String dirname;
		System.out.println("Please enter the name of the period you wish to create ");
		dirname = sc.nextLine();
		String address  = userSpace.getUserSpaceFile().getAbsolutePath()+"\\";		
		File period = new File(address+dirname);
		period.mkdir();
		userSpace.addPeriod(dirname,period);
		userSpace.save();
	}

	@Override
	public void makePeriod() {
		captureConfiguration();
		createPeriod();
	}
	
	
	
	

	
	
}
