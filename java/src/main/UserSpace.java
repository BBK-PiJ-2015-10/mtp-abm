package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;





public class UserSpace implements Serializable {
	
	private File file;
	
	private Map<String,File> configurations = new HashMap<>();
	
	public UserSpace() {
	}
	
	public File getUserSpaceFile(){
		return this.file;
		
	}
	
	public void FileSetUserSpaceFile(File file){
		this.file=file;
	}
	
	
	public void addConfiguration(String configname, File file){
		configurations.put(configname, file);
	}
	
	
	public Set<String> getConfigurationsNames(){
		return configurations.keySet();
	}
	
	public File getConfiguration(String configName){
		return configurations.get(configName);
	}
	
	public void save(){
		String address = "C:\\Users\\YasserAlejandro\\mtp\\mtp-abm\\";
		String directory = file.getName();
		
		try (ObjectOutputStream encode = new ObjectOutputStream(new FileOutputStream(address+directory+"\\"+file.getName()+".dat"));)
		{
			encode.writeObject(file);
			encode.writeObject(configurations);
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	
	

}
