package client;

import java.io.File;

/**
 * A class implementing ClientCosts needs to:
 * - Calculate the client costs associated with a particular ABC period run.
 * - Provide access to the file that holds the clients costs for an ABC period.
 */
public interface ClientCosts {
	
	/**
	 * Calculates the client costs associated with a particular ABC period
	 * @return a Boolean true if the client costs calculations were completed
	 * successfully, false if the operation failed.
	 */
	boolean calculateClientCosts();
	
	/** 
	 * @return a file that holds the client costs for a period.
	 */
	File getClientCostsFile();
	
}
