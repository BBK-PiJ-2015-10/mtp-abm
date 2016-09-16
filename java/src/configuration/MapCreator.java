package configuration;

import java.util.Scanner;

/**
 * A class implementing MapCreator needs to:
 * - Create a map destination to host the mapping of general ledger dimensions of
 * interest to BPA driver sources.
 * - Extract from a general ledger source the unique value sets of dimensions
 * of interest and capture from the user the BPA source driver for each unique
 * set of values. 
 */
public interface MapCreator {
	
	/**
	 * @param configurationManager that holds references to general ledger source
	 * and metadata about BPA sources.
	 * @param sc a scanner to capture the BPA driver provided by the user to map to
	 * each unique set of general ledger dimension(s) of interest.
	 * @param mapName specifies the name to provide to the map destination created.
	 * @return Boolean true if the mapping operation succeeded, false if the process
	 * failed.
	 */
	boolean createMap(ConfigurationManagerAbstract configurationManager, Scanner sc, String mapName);

}
