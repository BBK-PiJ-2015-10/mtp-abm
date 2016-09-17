package configuration;

import java.io.File;

/*
 * A CSV extension of ConfigurationMaker.
 */
public class ConfigurationMakerImplCSV extends ConfigurationMakerAbstract {
		
	public ConfigurationMapper initConfigMapper(File config){
		return new ConfigurationMapperImplCSV(new ConfigurationManagerCSV(config));
	}
	
}
