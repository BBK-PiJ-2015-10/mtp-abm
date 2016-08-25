package configuration;


import java.util.Scanner;
import validator.FileValidator;
import validator.FileValidatorImplBpaMap;

public abstract class MapCreatorAbstract implements MapCreator {
	
	protected boolean manualFlag;
	
	protected FileValidator fileValidator = new FileValidatorImplBpaMap();
		
	public MapCreatorAbstract(boolean manualFlag){
		this.manualFlag=manualFlag;
	}

	public abstract boolean createMap(ConfigurationManagerAbstract configurationManager, Scanner keyboard, String mapName);
	
}
