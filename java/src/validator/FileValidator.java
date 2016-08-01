package validator;

import java.io.File;

import configuration.ConfigurationManager;

public interface FileValidator {
	
	boolean validateInput(File file, String parameterName, ConfigurationManager configMgr);
	

}
