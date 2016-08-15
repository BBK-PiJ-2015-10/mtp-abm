package old;

import java.io.File;

//import configuration.ConfigurationManagerCSV;

public interface FileValidatorOld {
	
	boolean validateInput(File file, String parameterName, ConfigurationManagerCSVOld configMgr);
	

}
