package configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.io.BufferedReader;

public class ConfigurationManager implements Serializable {

	private File file;
	
	private Map<String,File> filesMap = new HashMap<>();
	
	private File glFile;
	
	private Map<String,Set<String>> bpaFilesAttributesMap = new HashMap<>();
	
	private Map<String,Set<String>> bpaFilesMainAttribustesMap = new HashMap<>();
	
	private Map<String,Set<String>> glFilesAttributesMap = new HashMap<>();
	
	private Map<String,Set<String>> glFilesMainAttribustesMap = new HashMap<>();
	
	private BufferedReader in = null;
	
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
		
	public Set<String> getBPAFileNames(){
		return filesMap.keySet();
	}
	
	public File getBPAFile(String name){
		return filesMap.get(name);
	}
	
	public void setGLFile(String filename){
		glFile = filesMap.remove(filename);	
	}
	
	public File getGLFile(){
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
	}
	
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
	
	public void grabFileAttributes(File file, Map map){
		try {
			in = new BufferedReader(new FileReader(file));
			String line;
			line = in.readLine();
			String[] strArray = line.split(",");
			Set<String> fieldsSet = new HashSet<>();
			for (int i=0; i<strArray.length;i++){
				fieldsSet.add(strArray[i]);
			}
			map.put(file.getName(), fieldsSet);
		}
		catch (FileNotFoundException ex){
			throw new RuntimeException(ex);
		}
		catch(IOException ex){
			throw new RuntimeException(ex);
		}
	
	}	
	
	
	public boolean grabFilesAttributes() {	
		boolean isSuccesful=false;
		try {
			for (String input: getBPAFileNames()){
				grabFileAttributes(filesMap.get(input),bpaFilesAttributesMap);
			}
			grabFileAttributes(glFile,glFilesAttributesMap);
			isSuccesful=true;
		} catch (RuntimeException ex) {
			isSuccesful=false;
		}
		return isSuccesful;	
	}
	
	public void filesMainAttributes(){
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
