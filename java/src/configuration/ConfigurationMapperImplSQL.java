package configuration;

import java.util.Scanner;
import java.util.NoSuchElementException;

/*
 * An extension of ConfigurationMapperAbstract to support an SQL ABC configuration.
 */
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
