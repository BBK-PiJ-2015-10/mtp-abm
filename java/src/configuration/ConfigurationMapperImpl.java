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
		//Scanner sc = new Scanner(System.in);
		configurationManager.loadFilesMap();
		System.out.println("Please enter the name and extension of the file that contains the general ledger data");
		//String glName = sc.nextLine();
		String glName = sc.next();
		configurationManager.setGLFile(glName);
		configurationManager.grabFilesAttributes();
		configurationManager.loadGlFilesMainAttributes();
		configurationManager.loadBpaFilesMainAttributes();
		configurationManager.save();
	}
	
	@Override 
	public void mapFiles(Scanner sc){
		execManager(sc);
		createmap();
		
	}
	
	public void createmap(){
		mapCreator = new MapCreatorImpl();
		mapCreator.createMap(configurationManager);
	}
	

}
