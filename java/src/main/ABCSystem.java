package main;

/**
 * A class implementing ABCSystem needs to:
 * - Provide access to the creation/retrieval of a UserSpace.
 * Configuration, Period, and ABC Reports.
 * - Serve as an interface to capture input from the user.
 */
public interface ABCSystem {
	
	/**
	 * Starts the provision of the following ABC services to a user:
	 * - Creation/retrieval of a userSpace.
	 * - Creation/retrieval of a configuration.
	 * - Creation/retrieval of a period.
	 * - Execution of ABC Reports.
	 */ 
	void runABC();
	
}
