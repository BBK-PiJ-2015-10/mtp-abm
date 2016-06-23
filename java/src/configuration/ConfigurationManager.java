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
	
	private Map<String,List<String>> bpaFilesMainAttributesMap = new HashMap<>();
	
	private Map<String,Set<String>> glFilesAttributesMap = new HashMap<>();
	
	private Map<String,List<String>> glFilesMainAttributesMap = new HashMap<>();
	
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
	
	public void loadBpaFilesMainAttributes(){
		for (String input : bpaFilesAttributesMap.keySet()){			
			boolean valid1=false;
			List<String> temp = new LinkedList<>();
			System.out.print("For: " +input);
			while (!valid1){
				valid1=readEntry(input," Enter the activity driver from the below options ",temp,bpaFilesAttributesMap,1);
				if (!valid1){
					System.out.println("Incorrect selection");
				}	
			}
			valid1=false;	
			while (!valid1){
				valid1=readEntry(input," Enter the name of the column with the driver consumption data ",temp,bpaFilesAttributesMap,1);
				if (!valid1){
					System.out.println("Incorrect selection");
			    }
		    }
			bpaFilesMainAttributesMap.put(input,temp);
		}	
	}
	
	public void loadGlFilesMainAttributes(){
		for (String input : glFilesAttributesMap.keySet()){
			boolean valid1=false;
			List<String> temp = new LinkedList<>();
			System.out.print("For: " +input);
			while (!valid1){
				//valid1=readEntry(input," Enter the attribute(s) ",temp,glFilesAttributesMap);
				//if (!valid1){
					//System.out.println("Incorrect selection");
				//}	
			}
			
			
		}
		
		
		System.out.println("Enter the attribute(s) to be mapped to Activities from the below options");
		System.out.println(glFilesAttributesMap.keySet());
		//System.out.println(glFilesAttributesMap.get(glFile.getName()));
		//for (String input : glFilesAttributesMap.get(glFile.getName())){	
			
		//}
	}
	
	
	public boolean readEntry(String input, String message,List<String> accumulator, Map<String,Set<String>> inputMap,int maxEntry){
		int counter =0;
		boolean result = false;
		System.out.println(message);
		System.out.println(inputMap.get(input));
		String selection = sc.nextLine();
		String[] selectionarray = selection.split(" ");
		for (int i=0;i<selectionarray.length;i++){
			if (counter >= maxEntry){
				return false;
			}
			result = inputMap.get(input).contains(selectionarray[i]) && !accumulator.contains(selectionarray[i]);
			if (!result){
				return false;
			}
			else {
				accumulator.add(selectionarray[i]);
				counter ++;
			}		
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
		return this.bpaFilesMainAttributesMap;
	}
	
	public Map<String,List<String>> getGlMainFilesAttributesMap(){
		return this.glFilesMainAttributesMap;
	}	
	
	public void save(){
		try (ObjectOutputStream encode = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()+"\\"+file.getName()+".dat"));)
		{
			encode.writeObject(file);
			encode.writeObject(bpaFilesMap);
			encode.writeObject(glFile);
			encode.writeObject(bpaFilesAttributesMap);
			encode.writeObject(glFilesAttributesMap);
			encode.writeObject(bpaFilesMainAttributesMap);
			encode.writeObject(glFilesMainAttributesMap);
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
			glFilesAttributesMap=(Map<String,Set<String>>)incode.readObject();
			bpaFilesMainAttributesMap=(Map<String,List<String>>)incode.readObject();
			glFilesMainAttributesMap=(Map<String,List<String>>)incode.readObject();
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
