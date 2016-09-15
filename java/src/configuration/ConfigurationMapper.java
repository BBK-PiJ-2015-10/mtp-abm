package configuration;

import java.util.Scanner;

/**
 * @author YasserAlejandro
 * Classes implementing a ConfigurationMapper should:
 * - Create a map file to host the mapping of general ledger dimensions of
 * interest to BPA driver sources.
 * - Extract from a general ledger source the unique value sets of dimensions
 * of interest and capture from the user the BPA source driver for each unique
 * set of values. 
 */
public interface ConfigurationMapper {
	
	/**
	 * Creates a map file and executes the mapping of general ledger dimensions
	 * of interests to BPA driver sources.
	 * 
	 * @param sc a scanner object to capture data from the user.
	 * @return a Boolean true if the mapping of files was successful. Return a 
	 * Boolean false if the operation failed.
	 */
	boolean mapFiles(Scanner sc);

}
