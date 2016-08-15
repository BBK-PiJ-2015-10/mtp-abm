package configuration;

import java.io.File;
import java.util.Scanner;

import sqlimpl.MapCreatorFactorySQL;

import java.util.Arrays;

import java.util.NoSuchElementException;

public class ConfigurationMapperImplSQL extends ConfigurationMapperAbstract implements ConfigurationMapper {
	
	public ConfigurationMapperImplSQL(ConfigurationManagerAbstract configurationManager){
		super(configurationManager);
		mapCreatorFactory = new MapCreatorFactorySQL();
	}

	public boolean execManager(Scanner sc) {
		try {
			configurationManager.loadFilesMap();
			((ConfigurationManagerSQL)configurationManager).captureGLConnectionSettings(sc);
			configurationManager.grabFilesAttributes();
			configurationManager.loadGlFilesMainAttributes(sc);
			configurationManager.loadBpaFilesMainAttributes(sc);
			configurationManager.save();
		    return true;
		} catch (NoSuchElementException ex){
			return false;
		} catch (NullPointerException ex){
			return false;
		}
	}
	
	@Override 
	public boolean mapFiles(Scanner sc){
		return (execManager(sc) && createMap(sc));	
	}
	
	public boolean createMap(Scanner sc){
		if (sc==null){
			return false;
		}
		
		boolean validEntry;
		mapCreatorFactory.presentChoices();
		Integer choice = null;
		do {
			try {
				choice = Integer.parseInt(sc.nextLine());
				validEntry=mapCreatorFactory.getMapCreatorOptions().containsKey((choice));
			} catch (NumberFormatException ex){
				validEntry=false;
			}
			if (!validEntry){
				System.out.println("Invalid entry, please type a valid option");
				mapCreatorFactory.presentChoices();
			}
		} while (!validEntry);
		return mapCreatorFactory.getMapCreator(choice).createMap(configurationManager,sc,"glbpamap.csv");
	}
	

}
