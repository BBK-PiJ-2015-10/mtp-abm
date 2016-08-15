package configuration;

import java.util.Scanner;

public interface MapCreator {
	
	boolean createMap(ConfigurationManagerAbstract configurationManager, Scanner sc, String mapName);

}
