package period;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

import configuration.ConfigurationManagerAbstract;

public interface PeriodMaker {

	
	void makePeriod(Scanner sc,String configName);
	
	//for the sake of testing
	void save();
	
	//for the sake of testing
	boolean capture(String periodName);
	
    // Need to write JUnit tests
	ConfigurationManagerAbstract getConfiguration();
	
	//Need to write JUnit tests
	File getPeriod();
	
	File getBpaCosts();
	
	void setBpaCosts(File bpaCosts);
	
	//Need to write JTest
	void setClientCosts(File clientCosts);
	
	//Need to write JTest
	File getClientCosts();
	
	//This is a test
	Map<String,File> getPeriodFiles();
	
	//This is a test
	Map<String,String> getDriversMap();

	
}
