package configuration;

import java.util.Scanner;

public interface MapCreator {
	
	boolean createMap(ConfigurationManager configurationManager, Scanner sc, String mapName);

}
