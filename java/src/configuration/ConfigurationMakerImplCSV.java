package configuration;

import java.util.Scanner;

import user.UserSpace;

import java.io.File;

import sqlimpl.*;


public class ConfigurationMakerImplCSV extends ConfigurationMakerAbstract {
		
	public ConfigurationMapper initConfigMapper(File config){
		return new ConfigurationMapperImplCSV(new ConfigurationManagerCSV(config));
	}
	
}
