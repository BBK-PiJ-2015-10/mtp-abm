package user;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 *  A class that holds information (configurations, types of configurations,
 *  periods) about a User.
 */
public class UserSpace implements Serializable {
	
	/*
	 * This is the file associated with this UserSpace.
	 */
	private File file;
	
	/*
	 * This structure keeps a reference to all the configurations files
	 * associated with a UserSpace.
	 */
	private Map<String,File> configurations = new HashMap<>();
	
	//This is a map of configuration type names to configuration names.
	private Map<String,Set<String>> configTypesMap = new HashMap<>();
	
	//This is a map of configuration names to period names.
	private Map<String,Set<String>> configPeriodMap = new HashMap<>();
	
	/*
	 * This map provides a reference to all the period Files associated
	 * with this configuration.
	 */	
	private Map<String,File> periods = new HashMap<>();
	
	public UserSpace() {
	}
		
	public void FileSetUserSpaceFile(File file){
		this.file=file;
	}	
	
	public File getUserSpaceFile(){
		return this.file;
	}
	

	// Adds a configuration to a UserSpace object.  
	public void addConfiguration(String configname, File file,String type){
		configurations.put(configname, file);
		if (configTypesMap.containsKey(type)){
			configTypesMap.get(type).add(configname);
		}
		else {
			Set<String> temp = new HashSet<>();
			temp.add(configname);
			configTypesMap.put(type, temp);
		}
	}
	
	//Removes a configuration from a UserSpace object.
	public void removeConfiguration(String configName, String type){
		configurations.remove(configName);
		configTypesMap.get(type).remove(configName);
		configPeriodMap.remove(configName);
	}
	
	public File getConfiguration(String configName){
		return configurations.get(configName);
	}
		
	public Set<String> getConfigurationsNames(){
		return configurations.keySet();
	}
	
	/*
	 * Returns a Boolean true if a configuration name exists
	 * within this UserSpace, and false if it doesn't.
	 */
	public boolean validConfiguration(String configName){
		return configurations.containsKey(configName);
	}
	
	// Adds a period to a UserSpace object. 
	public void addPeriod(String periodName, File file,String configType){
		periods.put(periodName, file);
		if (configPeriodMap.containsKey(configType)){
			configPeriodMap.get(configType).add(periodName);
		}
		else {
			Set<String> temp = new HashSet<>();
			temp.add(periodName);
			configPeriodMap.put(configType, temp);
		}
	}
	
	/*
	 * Returns a particular period file reference that exists in this
	 * UserSpace.
	 */
	public File getPeriod(String periodName){
		return periods.get(periodName);
	}
		
	//Returns a set with the names of all the periods part of this UserSpace.
	public Set<String> getPeriodNames(){
		return periods.keySet();
	}
	
	//Need to create JUnit test for this method
	public boolean validPeriod(String periodName){
		return periods.containsKey(periodName);
	}
	
	public Map<String,Set<String>> getConfigTypesMap(){
		return this.configTypesMap;
	}
	
	public String getConfigurationType(String configurationName){
		for (String input : configTypesMap.keySet()){
			if(configTypesMap.get(input).contains(configurationName)){
				return input;
			}
		}
		return null;
	}
	

	public String getPeriodConfigurationType(String periodName){
		for (String input : configPeriodMap.keySet()){
			if(configPeriodMap.get(input).contains(periodName)){
				return getConfigurationType(input);
			}
		}
		return null;
	}
	
	
	//Persistent storage of all data structures associated with this UserSpace.
	public void save(){
		try (ObjectOutputStream encode = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()+"\\"+file.getName()+".dat"));)
		{
			encode.writeObject(file);
			encode.writeObject(configurations);
			encode.writeObject(periods);
			encode.writeObject(configTypesMap);
			encode.writeObject(configPeriodMap);
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	//Retrieval of all data structures associated with this UserSpace.
	public boolean capture(String directoryname){
		boolean isPresent;
		String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\";
		try (ObjectInputStream incode = new ObjectInputStream(new FileInputStream(address+directoryname+"\\"+directoryname+".dat"));)
		{
			file = (File)incode.readObject();
			configurations=(Map<String,File>)incode.readObject();
			periods=(Map<String,File>)incode.readObject();
			configTypesMap=(Map<String,Set<String>>)incode.readObject();
			configPeriodMap=(Map<String,Set<String>>)incode.readObject();
			isPresent = true;
		} catch (ClassNotFoundException ex){
			isPresent = false;
		} catch (IOException ex2){
			isPresent = false;;
		} 
		return isPresent;
	}
		

}
