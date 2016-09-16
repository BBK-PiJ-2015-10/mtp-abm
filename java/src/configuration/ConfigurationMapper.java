package configuration;

import java.util.Scanner;

/**
 * A class implementing a ConfigurationMapper needs to:
 * - Direct the process of mapping a general ledger to a BPAs leveraging
 * a ConfigurationManager and a MapCreator.
 */
public interface ConfigurationMapper {
	
	/**
	 * Directs the process of creating a general ledger to BPA map.
	 * 
	 * @param sc a scanner object to capture the mapping choice selected by a
	 * user.
	 * @return a Boolean true if the mapping of files was successful. Return a 
	 * Boolean false if the operation failed.
	 */
	boolean mapFiles(Scanner sc);

}
