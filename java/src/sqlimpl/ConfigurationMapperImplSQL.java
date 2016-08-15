package sqlimpl;

import java.io.File;
import java.util.Scanner;

import configuration.ConfigurationManagerCSV;
import configuration.ConfigurationManagerSQL;
import configuration.ConfigurationMapper;
import configuration.MapCreator;
import configuration.MapCreatorFactoryCSV;

import java.util.Arrays;

import java.util.NoSuchElementException;

public class ConfigurationMapperImplSQL implements ConfigurationMapper {
	
	private ConfigurationManagerSQL configurationManager;
	
	private MapCreatorFactorySQL mapCreatorFactory = new MapCreatorFactorySQL();
	
	private MapCreator mapCreator;
	
	public ConfigurationMapperImplSQL(ConfigurationManagerSQL configurationManager){
		this.configurationManager=configurationManager;
	}

	public boolean execManager(Scanner sc) {
		try {
			configurationManager.loadFilesMap();
			/*
			boolean validEntry=false;
			String glName;
			do {
				System.out.println("Please enter the name and extension of the file that contains the general ledger data");
				glName = sc.nextLine();
				if (glName == null){
					return false;
				}
				if (Arrays.asList(configurationManager.getFile().list()).contains(glName)){
					validEntry=true;
				} else{
					System.out.println("invalid name");
				}
			} while (!validEntry);
			*/
			//configurationManager.setGLFile(glName);
			configurationManager.captureGLConnectionSettings(sc);
			//configurationManager.setUpGLConnection();
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
