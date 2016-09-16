package configuration;

import user.UserSpace;
import java.util.Scanner;

/**
 * A class implementing ConfigurationMaker will create a Configuration space 
 * on a particular location and start a ConfigurationMapper object.
 */
public interface ConfigurationMaker {
	
	/**
	 * This method captures the desired configName from the user, 
	 * creates a directory on disk based on that name, and triggers
	 * a ConfigurationMapper object.
	 * 
	 * @param userSpace that will host this configuration.
	 * @param sc a scanner will capture user input (configuration name).
	 * @param configType the type of configuration to create.
	 * @return boolean true if the configuration making processes succeeded,
	 * false if not.
	 */
	boolean makeConfiguration(UserSpace userSpace,Scanner sc,String configType);
	
	

}
