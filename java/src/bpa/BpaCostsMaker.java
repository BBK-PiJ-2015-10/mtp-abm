package bpa;

import java.util.Scanner;


import period.PeriodMaker;

/**
 * A class that implements BpaCostsMaker needs to:
 * - Create a BpaCosts file.
 * - Populate the BpaCosts file with the Bpa costs of a period.
 */
public interface BpaCostsMaker {
	
	/**
	 * Creates and populates a BpaCosts file.
	 * @param sc a scanner object to facilitate the capturing of user 
	 * input.
	 * @return a Boolean with a true value if the population
	 * of the BpaCosts file is successful, and a false if there is an error
	 * in the process.
	 */
	boolean createbpaCosts(Scanner sc);
	
	/**
	 * @return the PeriodMaker associated with this BpaCostMaker
	 */
	PeriodMaker getPeriodMaker();
	
	/**
	 * Creates a BpaCosts file
	 * @return a Boolean of true if the creation of the file is successful,
	 * false if the file creation failed. 
	 */
	boolean createBpaCostsFile();

}
