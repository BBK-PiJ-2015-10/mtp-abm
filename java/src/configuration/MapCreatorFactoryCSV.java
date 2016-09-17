package configuration;

/*
 * A CSV extension of the MapCreatorFactoryAbstract class.
 */
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
