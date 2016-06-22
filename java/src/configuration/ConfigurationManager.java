package configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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

import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class ConfigurationManager implements Serializable {

	private File file;
	
	private Map<String,File> bpaFilesMap = new HashMap<>();
	
	private File glFile;
	
	private Map<String,Set<String>> bpaFilesAttributesMap = new HashMap<>();
	
	private Map<String,List<String>> bpaFilesMainAttribustesMap = new HashMap<>();
	
	private Map<String,Set<String>> glFilesAttributesMap = new HashMap<>();
	
	private Map<String,List<String>> glFilesMainAttribustesMap = new HashMap<>();
	
	private BufferedReader in = null;
	
	private Scanner sc = new Scanner(System.in);
	
	public ConfigurationManager(File file){
		this.file=file;
	}
		
	public void setFile(File file){
		this.file=file;
	}
	
	public File getFile(){
		return this.file;
	}
		
	public void loadFilesMap(){
		if(file.exists() && file.isDirectory()){
			File [] filenames = file.listFiles();
			for (int i=0;i<filenames.length;i++){
				if (!(filenames[i].getName()+".dat").equals(file.getName()))
				{
					bpaFilesMap.put(filenames[i].getName(), filenames[i]);
				}
			}		
		}			
	}	
	
	public Map<String,File> getBPAFilesMap(){
		return this.bpaFilesMap;
	}
		
	public File getBPAFile(String name){
		return bpaFilesMap.get(name);
	}
	
	public void setGLFile(String filename){
		glFile = bpaFilesMap.remove(filename);	
	}
	
	public File getGLFile(){
		return glFile;
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
			for (String input: bpaFilesMap.keySet()){
				grabFileAttributes(bpaFilesMap.get(input),bpaFilesAttributesMap);
			}
			
			grabFileAttributes(glFile,glFilesAttributesMap);
			isSuccesful=true;
		} catch (RuntimeException ex) {
			isSuccesful=false;
		}
		return isSuccesful;	
	}
	
	public void loadBpafilesMainAttributes(){
		for (String input : bpaFilesAttributesMap.keySet()){			
			boolean valid1=false;
			List<String> temp = new LinkedList<>();
			System.out.print("For: " +input);
			while (!valid1){
				valid1=readEntry(input," Enter the activity driver from the below options ",temp);
				if (!valid1){
					System.out.println("Incorrect selection");
				}	
			}
			valid1=false;	
			while (!valid1){
				valid1=readEntry(input," Enter the nale of the column with the driver consumption data ",temp);
				if (!valid1){
					System.out.println("Incorrect selection");
			    }
		    }
		}	
	}
	
	public void loadGlFilesMainAttributes(){
		System.out.println("Enter the attribute(s) to be mapped to Activities from the below options");
		System.out.println(glFilesAttributesMap.keySet());
		//System.out.println(glFilesAttributesMap.get(glFile.getName()));
		//for (String input : glFilesAttributesMap.get(glFile.getName())){	
			
		//}
	}
	
	public boolean readEntry(String input, String message,List<String> accumulator){
		boolean result = false;
		System.out.println(message);
		System.out.println(bpaFilesAttributesMap.get(input));
		String selection = sc.nextLine();
		result = bpaFilesAttributesMap.get(input).contains(selection) && !accumulator.contains(selection);
		if (result){
			accumulator.add(selection);
		}
		return result;
	}
	
	public Map<String,Set<String>> getBpaFilesAttributesMap(){
		return this.bpaFilesAttributesMap;
	}
	
	public Map<String,Set<String>> getGlFilesAttributesMap(){
		return this.glFilesAttributesMap;
	}
	
	public Map<String,List<String>> getBpaMainFilesAttributesMap(){
		return this.bpaFilesMainAttribustesMap;
	}
	
	public Map<String,List<String>> getGlMainFilesAttributesMap(){
		return this.glFilesMainAttribustesMap;
	}	
	
	public void save(){
		try (ObjectOutputStream encode = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()+"\\"+file.getName()+".dat"));)
		{
			encode.writeObject(file);
			encode.writeObject(bpaFilesMap);
			encode.writeObject(glFile);
			encode.writeObject(bpaFilesAttributesMap);
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
			bpaFilesMap=(Map<String,File>)incode.readObject();
			glFile= (File)incode.readObject();
			bpaFilesAttributesMap=(Map<String,Set<String>>)incode.readObject();
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
