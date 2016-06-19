package main;

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

public class ConfigurationManager implements Serializable {

	private File file;
	
	private Map<String,File> filesMap = new HashMap<>();
	
	private File glFile;
	
	//public ConfigurationManager() {};
	
	public ConfigurationManager(File file){
		this.file=file;
	}
		
	
	public void setFile(File file){
		this.file=file;
	}
	
	public File getFile(){
		return this.file;
	}
		
	public void scanConfig(){
		if(file.exists() && file.isDirectory()){
			File [] filenames = file.listFiles();
			for (int i=0;i<filenames.length;i++){
				if (!(filenames[i].getName()+".dat").equals(file.getName()))
				{
					filesMap.put(filenames[i].getName(), filenames[i]);
				}
			}		
		}			
	}	
		
		
	public Set<String> getFileNames(){
		return filesMap.keySet();
	}
	
	public void setGLFile(String filename){
		glFile = filesMap.remove(filename);	
	}
	
	public File getFLFile(){
		return glFile;
	}
	
	public void save(){
		try (ObjectOutputStream encode = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()+"\\"+file.getName()+".dat"));)
		{
			encode.writeObject(file);
			encode.writeObject(filesMap);
			encode.writeObject(glFile);
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
	};
	
	public boolean capture(String configurationname){
		boolean isPresent;
		try (ObjectInputStream incode = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()+"\\"+configurationname+".dat"));)
		{
			file = (File)incode.readObject();
			filesMap=(Map<String,File>)incode.readObject();
			glFile= (File)incode.readObject();
			isPresent = true;
		} catch (ClassNotFoundException ex){
			isPresent = false;
		} catch (IOException ex2){
			isPresent = false;;
		} catch (NullPointerException ex){
			isPresent = false;
		}
		return isPresent;
	}
	
	
}
