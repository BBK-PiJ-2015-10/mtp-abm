package configuration;

import java.io.File;

/*
 * An SQL extension of ConfigurationMaker.
 */
public class ConfigurationMakerImplSQL extends ConfigurationMakerAbstract {

	public ConfigurationMapper initConfigMapper(File config){
		return new ConfigurationMapperImplSQL(new ConfigurationManagerSQL(config));
	}

	
}
