package configuration;

import java.util.Map;
import java.util.HashMap;

public class MapCreatorFactory {
	
	private Map<Integer,String> mapCreatorOptions = new HashMap();
	
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
	
	public MapCreator getMapCreator(int choice){
		if (mapCreatorOptions.isEmpty()){
			loadOptions();
		}
		switch(choice){
			case 1: return new MapCreatorImpl(false);
			case 2: return new MapCreatorImpl(true);
			default: return null;
		}
	}
	
}
