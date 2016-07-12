package period;

import java.io.File;
import java.util.Map;
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
	
	//Need to write JUnit tests
	File getPeriod();
	
	
	//This is a test
	Map<String,File> getPeriodFiles();
	
	//This is a test
	Map<String,String> getDriversMap();

	
}
