package configuration;

import java.util.Map;
import java.util.HashMap;

/*
 * An abstract class that hosts a set of available user Options, presents the 
 * options to the user, and provides a MapCreator based on a user selection.
 */
public abstract class MapCreatorFactoryAbstract {
	
	protected Map<Integer,String> mapCreatorOptions = new HashMap();
	
	public Map<Integer,String> getMapCreatorOptions(){
		return this.mapCreatorOptions;
	}
	 
	public void loadOptions(){
		mapCreatorOptions.put(1,"this application");
		mapCreatorOptions.put(2,"a manual table reference");
	}
	
	
	public boolean presentChoices(){
		if (mapCreatorOptions.isEmpty()){
			loadOptions();
		}
		for (Integer inputKey : mapCreatorOptions.keySet()){
			System.out.println("For mapping the GL to BPA via " +mapCreatorOptions.get(inputKey) + " please type: " +inputKey);
		}
		return true;
	}
	
	public abstract MapCreator getMapCreator(int choice);
	
}
