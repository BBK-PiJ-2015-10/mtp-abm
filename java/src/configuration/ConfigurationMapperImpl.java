package configuration;

import java.io.File;
import java.util.Scanner;

import java.util.NoSuchElementException;

public class ConfigurationMapperImpl implements ConfigurationMapper {
	
	private ConfigurationManager configurationManager;
	
	private MapCreator mapCreator;
	
	public ConfigurationMapperImpl(ConfigurationManager configurationManager){
		this.configurationManager=configurationManager;
	}

	public boolean execManager(Scanner sc) {
		try {
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
		} catch (NoSuchElementException ex){
			return false;
		}
		return true;
	}
	
	@Override 
	public boolean mapFiles(Scanner sc){
		return (execManager(sc) && createMap(sc));	
	}
	
	public boolean createMap(Scanner sc){
		mapCreator = new MapCreatorImpl();
		mapCreator.createMap(configurationManager,sc,"glbpamap.csv");
		return true;
	}
	

}
