package configuration;

import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

import java.util.NoSuchElementException;

public class ConfigurationMapperImplCSV extends ConfigurationMapperAbstract implements ConfigurationMapper {
		
	
	public ConfigurationMapperImplCSV(ConfigurationManagerAbstract configurationManager){
		super(configurationManager);
		mapCreatorFactory = new MapCreatorFactoryCSV();		
	}

	public boolean execManager(Scanner sc) {
		try {
			configurationManager.loadFilesMap();
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
			((ConfigurationManagerCSV)configurationManager).setGLFile(glName);
			configurationManager.grabFilesAttributes();
			configurationManager.loadGlFilesMainAttributes(sc);
			configurationManager.loadBpaFilesMainAttributes(sc);
			configurationManager.save();
		    return true;
		} catch (NoSuchElementException | NullPointerException ex){
			return false;
		} 
	}
	


}
