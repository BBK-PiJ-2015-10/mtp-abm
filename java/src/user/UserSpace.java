package user;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
	
	//Need to create JUnit Testing for this field
	private Map<String,File> periods = new HashMap<>();
	
	public UserSpace() {
	}
		
	public void FileSetUserSpaceFile(File file){
		this.file=file;
	}	
	
	public File getUserSpaceFile(){
		return this.file;
	}
	
	
	public void addConfiguration(String configname, File file){
		configurations.put(configname, file);
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
	public void addPeriod(String periodName, File file){
		periods.put(periodName, file);
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
	
	public void save(){
		try (ObjectOutputStream encode = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()+"\\"+file.getName()+".dat"));)
		{
			encode.writeObject(file);
			encode.writeObject(configurations);
			encode.writeObject(periods);
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
	}
		
	public boolean capture(String directoryname){
		boolean isPresent;
		//System.out.println(file.getAbsolutePath()+"\\"+directoryname+".dat");
		String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\";
		try (ObjectInputStream incode = new ObjectInputStream(new FileInputStream(address+directoryname+"\\"+directoryname+".dat"));)
		//try (ObjectInputStream incode = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()+"\\"+directoryname+".dat"));)
		{
			file = (File)incode.readObject();
			configurations=(Map<String,File>)incode.readObject();
			periods=(Map<String,File>)incode.readObject();
			isPresent = true;
			
		} catch (ClassNotFoundException ex){
			isPresent = false;
		} catch (IOException ex2){
			isPresent = false;;
		} 
		
		return isPresent;
	}
		

}
