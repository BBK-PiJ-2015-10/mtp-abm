package configuration;

import java.util.Scanner;

public class ConfigurationMapperImpl implements ConfigurationMapper {
	
	private ConfigurationManager configurationManager;
	
	private MapCreator mapCreator;
	
	public ConfigurationMapperImpl(ConfigurationManager configurationManager){
		this.configurationManager=configurationManager;
	}

	public boolean execManager(Scanner sc) {
		configurationManager.loadFilesMap();
		System.out.println("Please enter the name and extension of the file that contains the general ledger data");
		String glName = sc.nextLine();
		if (glName == null){
			return false;
		}
		configurationManager.setGLFile(glName);
		configurationManager.grabFilesAttributes();
		configurationManager.loadGlFilesMainAttributes(sc);
		configurationManager.loadBpaFilesMainAttributes(sc);
		configurationManager.save();
		return true;
	}
	
	@Override 
	public boolean mapFiles(Scanner sc){
		return (execManager(sc) && createmap(sc));	
	}
	
	public boolean createmap(Scanner sc){
		mapCreator = new MapCreatorImpl();
		mapCreator.createMap(configurationManager,sc,"glbpamap.csv");
		return true;
	}
	

}
