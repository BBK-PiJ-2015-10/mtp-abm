package configuration;

import java.util.Scanner;

/*
 * An implementation of ConfigurationMapper. It leverages a ConfigurationManagerAbstract
 * and a MapCreatorFactoryAbstract fields to support the direction of the 
 * process of mapping a general ledger to a BPAs.
 */
public abstract class ConfigurationMapperAbstract implements ConfigurationMapper {
	
	protected ConfigurationManagerAbstract configurationManager;
	
	protected MapCreatorFactoryAbstract mapCreatorFactory;
	
	
	public ConfigurationMapperAbstract(ConfigurationManagerAbstract configurationManager){
		this.configurationManager=configurationManager;
	}

	
	@Override 
	public boolean mapFiles(Scanner sc){
		return (execManager(sc) && createMap(sc));	
	}
	
	public boolean createMap(Scanner sc){
		if (sc==null){
			return false;
		}
		boolean validEntry;
		mapCreatorFactory.presentChoices();
		Integer choice = null;
		do {
			try {
				choice = Integer.parseInt(sc.nextLine());
				validEntry=mapCreatorFactory.getMapCreatorOptions().containsKey((choice));
			} catch (NumberFormatException ex){
				validEntry=false;
			}
			if (!validEntry){
				System.out.println("Invalid entry, please type a valid option");
				mapCreatorFactory.presentChoices();
			}
		} while (!validEntry);
		return mapCreatorFactory.getMapCreator(choice).createMap(configurationManager,sc,"glbpamap.csv");
	}
	
	
	public abstract boolean execManager(Scanner sc);

}
