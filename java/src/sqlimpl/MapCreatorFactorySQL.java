package sqlimpl;

import java.util.Map;
import java.util.HashMap;

import configuration.MapCreator;
import configuration.MapCreatorFactoryAbstract;

public class MapCreatorFactorySQL extends MapCreatorFactoryAbstract {
	
	public MapCreator getMapCreator(int choice){
		if (mapCreatorOptions.isEmpty()){
			loadOptions();
		}
		switch(choice){
			case 1: return new MapCreatorImplSQL(false);
			case 2: return new MapCreatorImplSQL(true);
			default: return null;
		}
	}
	
}
