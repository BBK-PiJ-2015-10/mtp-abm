package configuration;


import java.util.Scanner;
import validator.FileValidator;
import validator.FileValidatorImpl;

public abstract class MapCreatorAbstract implements MapCreator {
	
	protected boolean manualFlag;
	
	protected FileValidator fileValidator = new FileValidatorImpl();
		
	public MapCreatorAbstract(boolean manualFlag){
		this.manualFlag=manualFlag;
	}

	public abstract boolean createMap(ConfigurationManagerAbstract configurationManager, Scanner keyboard, String mapName);
	
}
