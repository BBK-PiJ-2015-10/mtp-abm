package configuration;

import java.util.Scanner;

import user.UserSpace;

import java.io.File;

import sqlimpl.*;


public class ConfigurationMakerImplCSV extends ConfigurationMakerAbstract {
		
	public ConfigurationMapperAbstract initConfigMapper(File config){
		return new ConfigurationMapperImplCSV(new ConfigurationManagerCSV(config));
	}
	
}
