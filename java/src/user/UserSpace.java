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
	
	public UserSpace() {
	}
	
	public UserSpace(File file){
		this.file=file;
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
	
	public void save(){
		try (ObjectOutputStream encode = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()+"\\"+file.getName()+".dat"));)
		{
			encode.writeObject(file);
			encode.writeObject(configurations);
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
	}
		
	public boolean capture(String directoryname){
		boolean isPresent;
		//String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\";
		//try (ObjectInputStream incode = new ObjectInputStream(new FileInputStream(address+directoryname+"\\"+directoryname+".dat"));)
		try (ObjectInputStream incode = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()+"\\"+directoryname+".dat"));)
		{
			file = (File)incode.readObject();
			configurations=(Map<String,File>)incode.readObject();
			isPresent = true;
			
		} catch (ClassNotFoundException ex){
			isPresent = false;
		} catch (IOException ex2){
			isPresent = false;;
		}
		return isPresent;
	}
		

}
