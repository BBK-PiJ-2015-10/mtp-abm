package configuration;

import java.io.File;
import java.util.Scanner;
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

	

}
