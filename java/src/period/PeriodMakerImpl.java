package period;

import user.UserSpace;
import java.util.Scanner;

import configuration.ConfigurationManager;

public class PeriodMakerImpl implements PeriodMaker {
	
	private UserSpace userSpace;
	
	private ConfigurationManager configurationManager;
	
	private Scanner sc = new Scanner(System.in);
	
	public PeriodMakerImpl(UserSpace userSpace){
		this.userSpace = userSpace;
	}
	
	public boolean validateConfiguration(String configName){
		return userSpace.validConfiguration(configName);
	}

	@Override
	public void makePeriod() {
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
		
	
		
		
		// TODO Auto-generated method stub
		
	}

	
	
}
