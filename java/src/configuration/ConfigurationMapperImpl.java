package configuration;

import java.util.Scanner;

public class ConfigurationMapperImpl implements ConfigurationMapper {
	
	private ConfigurationManager configurationManager;
	
	private MapCreator mapCreator;
	
	public ConfigurationMapperImpl(){
	}
	
	public ConfigurationMapperImpl(ConfigurationManager configurationManager){
		this.configurationManager=configurationManager;
	}

	public void execManager(Scanner sc) {
		configurationManager.loadFilesMap();
		System.out.println("Please enter the name and extension of the file that contains the general ledger data");
		String glName = sc.nextLine();
		configurationManager.setGLFile(glName);
		configurationManager.grabFilesAttributes();
		configurationManager.loadGlFilesMainAttributes(sc);
		configurationManager.loadBpaFilesMainAttributes(sc);
		configurationManager.save();
	}
	
	@Override 
	public void mapFiles(Scanner sc){
		execManager(sc);
		createmap(sc);
		
	}
	
	public void createmap(Scanner sc){
		mapCreator = new MapCreatorImpl();
		mapCreator.createMap(configurationManager,sc);
	}
	

}
