package configuration;

import java.util.Scanner;

import user.UserSpace;

import java.io.File;

import sqlimpl.*;


public class ConfigurationMakerImplSQL extends ConfigurationMakerAbstract {

	public ConfigurationMapperAbstract initConfigMapper(File config){
		return new ConfigurationMapperImplSQL(new ConfigurationManagerSQL(config));
	}

	
}
