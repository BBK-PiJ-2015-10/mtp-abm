package report;

import java.util.Scanner;

/**
 * @author YasserAlejandro
 * A class implementing ReportGenerator needs to:
 * - Generate a report for a user based on a specified set of choices.
 */
public interface ReportGenerator {
	
	/**
	 * Captures a choice from a user.
	 * @param sc a scanner that will capture the report choice selection
	 * of the user
	 * @return a Boolean true if the choice provided by user is valid,
	 * a false if the input provided is not valid.
	 */
	boolean captureChoice(Scanner sc);
		
	/**
	 * Generates a report for a user
	 * @return a Boolean true if the generation a report for a user was
	 * successful, false if there was an issue on the generation of the 
	 * report.
	 */
	boolean generateReport(); 		  
	

	
}
	
	
	
	


