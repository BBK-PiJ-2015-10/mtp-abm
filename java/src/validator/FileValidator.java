package validator;

import java.io.File;

import configuration.ConfigurationManagerAbstract;

/**
 * A class implementing FileValidator needs to:
 * - Validate that the user input of a general ledger to BPA mapping file
 * are valid. 
 */
public interface FileValidator {
	
	/**
	 * Validates that the inputs of a general ledger to BPA mapping file are valid.
	 * @param file the source that contains the input to be validated.
	 * @param parameterName a String being captured to be validated.
	 * @param configMgr a ConfigurationManagerAbstract class that contains the information
	 * about the valid selection.
	 * @return a Boolean true if all the input provided on a file are valid, false if 
	 * any of the input provided are invalid or are missing.
	 */
	boolean validateInput(File file, String parameterName, ConfigurationManagerAbstract configMgr);
	

}
