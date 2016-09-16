package period;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

import configuration.ConfigurationManagerAbstract;

/**
 * @author YasserAlejandro
 * A class implementing the PeriodMaker interface need to:
 * - Capture a desired period name from the user.
 * - Create a period space on a particular location.
 * - Associate the period created to a particular configuration
 * and userSpace.
 * - Persistent storage and retrieval of elements associated with
 * a period. 
 */
public interface PeriodMaker {

	/**
	 * Captures desired period name from user, associates period to a
	 * particular configuration and UserSpace, creates a Period space.
	 * @param sc is a scanner to capture user input.
	 * @param configName is the configuration to be associated with the
	 * period.
	 */
	void makePeriod(Scanner sc,String configName);
	
	/**
	 * Persistent storage of all elements associated with a period.
	 */
	void save();
	
	/**
	 * Retrieval of elements associated with a period.
	 * @param periodName holds the name of the period wishing to be retrieved.
	 * @return a Boolean true if all the elements associated with a period
	 * were successfully retrieved, false if any of them failed.
	 */
	boolean capture(String periodName);
	
    /**
     * @return a ConfigurationManagerAbstract object associated with this period.
     */
	ConfigurationManagerAbstract getConfiguration();
	
	/**
	 * @return a File document associated with this period.
	 */
	File getPeriod();
	
	/**
	 * @return a File document hosting the BpaCosts for this period.
	 */
	File getBpaCosts();
	
	/**
	 * @param bpaCosts BPA File to associate with this period.
	 */
	void setBpaCosts(File bpaCosts);
	
	/**
	 * @param clientCosts Client File to associate with this period.
	 */
	void setClientCosts(File clientCosts);
	
	/**
	 * @return the ClienCosts file associated with this period. 
	 */
	File getClientCosts();
	
	/**
	 * @return a map encapsulating all the source files needed to 
	 * run a period.
	 */
	Map<String,File> getPeriodFiles();
	
	/**
	 * @return a map with the unique value set of general ledger dimension(s)
	 * of interest and their associated BPA source for this period.
	 */
	Map<String,String> getDriversMap();

	
}
