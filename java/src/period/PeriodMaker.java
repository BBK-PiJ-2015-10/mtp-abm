package period;

import java.util.Scanner;

import configuration.ConfigurationManager;

public interface PeriodMaker {

	void makePeriod(Scanner sc);
	
	//for the sake of testing
	void save();
	
	//for the sake of testing
	boolean capture(String periodName);
	
    // Need to write JUnit tests
	ConfigurationManager getConfiguration();
	
	
}
