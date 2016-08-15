package validator;

import java.io.File;

import configuration.ConfigurationManagerAbstract;

public interface FileValidator {
	
	boolean validateInput(File file, String parameterName, ConfigurationManagerAbstract configMgr);
	

}
