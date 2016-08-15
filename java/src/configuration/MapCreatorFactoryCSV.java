package configuration;

import java.util.Map;
import java.util.HashMap;

public class MapCreatorFactoryCSV extends MapCreatorFactoryAbstract {
	
	public MapCreator getMapCreator(int choice){
		if (mapCreatorOptions.isEmpty()){
			loadOptions();
		}
		switch(choice){
			case 1: return new MapCreatorImplCSV(false);
			case 2: return new MapCreatorImplCSV(true);
			default: return null;
		}
	}
	
}
