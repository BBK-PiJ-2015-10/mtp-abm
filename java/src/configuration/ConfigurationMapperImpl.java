package configuration;

import java.util.Scanner;

public class ConfigurationMapperImpl implements ConfigurationMapper {
	
	private ConfigurationManager configurationManager;
	
	public ConfigurationMapperImpl(){
	}
	
	public ConfigurationMapperImpl(ConfigurationManager configurationManager){
		this.configurationManager=configurationManager;
	}

	public void scanFiles() {
		Scanner sc = new Scanner(System.in);
		configurationManager.loadFilesMap();
		System.out.println("Please enter the name and extension of the file that contains the general ledger data");
		String glName = sc.nextLine();
		configurationManager.setGLFile(glName);
		configurationManager.grabFilesAttributes();
		configurationManager.save();
	}
	
	@Override 
	public void mapFiles(){
		scanFiles();
		
	}
	
	public void createMap(){
		
	}
	

}