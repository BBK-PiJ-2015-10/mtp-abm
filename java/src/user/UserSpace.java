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

public class UserSpace implements Serializable {
	
	private File file;
	
	private Map<String,File> configurations = new HashMap<>();
	
	private Map<String,Set<String>> configTypesMap = new HashMap<>();
	
	private Map<String,Set<String>> configPeriodMap = new HashMap<>();
	
	//Need to create JUnit Testing for this field
	private Map<String,File> periods = new HashMap<>();
	
	//private Map<String,String>
	
	public UserSpace() {
	}
		
	public void FileSetUserSpaceFile(File file){
		this.file=file;
	}	
	
	public File getUserSpaceFile(){
		return this.file;
	}
	
	
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
	
	//Need to add JUnit test for this method
	public boolean validConfiguration(String configName){
		return configurations.containsKey(configName);
	}
	
	//Need to create JT
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
	
	//Need to create JT
	public File getPeriod(String periodName){
		return periods.get(periodName);
	}
		
	//Need to create JT
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
