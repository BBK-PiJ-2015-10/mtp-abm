package main;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConfigurationStructure implements Serializable {

	private File file;
	
	private Map<String,File> files = new HashMap<>();
	
	private File glFile;
	
	public ConfigurationStructure() {};
	
	public ConfigurationStructure(File file){
		this.file=file;
	}
		
	
	public void setFile(File file){
		this.file=file;
	}
	
	public File getFile(){
		return this.file;
	}
	
	public void addFiles(String filename, File file){
		files.put(filename, file);
	}
	
	public void scanConfig(){
		String [] filenames = file.list();
	}	
		
		
	
	public Set<String> getFileNames(){
		return files.keySet();
	}
	
	public void save(){};
	
	public boolean capture(String directory){
		return false;
	}	
	
	
	
}
