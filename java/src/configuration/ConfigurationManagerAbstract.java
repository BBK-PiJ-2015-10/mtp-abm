package configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

import java.io.Serializable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

/*
 * A class that permanently hosts needed data structures to create and 
 * manage an ABC configuration.
 */
public abstract class ConfigurationManagerAbstract implements Serializable {

	protected File file;
	
	protected Map<String,File> bpaFilesMap = new HashMap<>();
	
	protected File glbpamapFile;
	
	protected Map<String,Set<String>> bpaFilesAttributesMap = new HashMap<>();
	
	//Data structure with the BPA sources attributes of interests.
	protected Map<String,List<String>> bpaFilesMainAttributesMap = new HashMap<>();
	
	protected Map<String,Set<String>> glFilesAttributesMap = new HashMap<>();
	
	//Data structure with the general ledger source attributes of interests.
	protected Map<String,List<String>> glFilesMainAttributesMap = new HashMap<>();
	
	protected BufferedReader in = null;
		
	public ConfigurationManagerAbstract(File file){
		this.file=file;
	}
		
	public void setFile(File file){
		this.file=file;
	}
	
	public File getFile(){
		return this.file;
	}
	
	public void setglbpamapFile(File glbpamapFile){
		this.glbpamapFile=glbpamapFile;
	}
	
	public File getglbpamapFile(){
		return this.glbpamapFile;
	}
	
	public void loadFilesMap(){
		if(file.exists() && file.isDirectory()){
			File [] filenames = file.listFiles();
			for (int i=0;i<filenames.length;i++){
				if (!(filenames[i].getName()).contains(".dat") && !(filenames[i].getName()).equals("glbpamap.csv"))
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
	
	public boolean grabFileAttributes(File file, Map map){
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
			return true;
		}
		catch (FileNotFoundException ex){
			throw new RuntimeException(ex);
		}
		catch(IOException ex){
			throw new RuntimeException(ex);
		}
	}
	
			
	public void loadBpaFilesMainAttributes(Scanner sc){
		for (String input : bpaFilesAttributesMap.keySet()){			
			boolean valid1=false;
			List<String> temp = new LinkedList<>();
			System.out.print("For: " +input);
			while (!valid1){
				valid1=readEntry(input," Enter the activity driver from the below options ",temp,bpaFilesAttributesMap,1,sc);
				if (!valid1){
					System.out.println("Incorrect selection");
				}	
			}
			valid1=false;	
			while (!valid1){
				valid1=readEntry(input," Enter the name of the column with the driver consumption data ",temp,bpaFilesAttributesMap,1,sc);
				if (!valid1){
					System.out.println("Incorrect selection");
			    }
		    }
			bpaFilesMainAttributesMap.put(input,temp);
		}	
	}
	
	public void loadGlFilesMainAttributes(Scanner sc){
		for (String input : glFilesAttributesMap.keySet()){
			boolean valid1=false;
			List<String> temp = new LinkedList<>();
			System.out.print("For: " +input);
			int maxEntry = glFilesAttributesMap.values().toString().split(" ").length;
			while (!valid1){
				temp.clear();
				valid1=readEntry(input," enter the attribute(s) to be mapped to Activities from the below options:",temp,glFilesAttributesMap,maxEntry-1,sc);
				if (!valid1){
					System.out.println("Incorrect selection");
				}	
			}
			valid1=false;	
			while (!valid1){
				valid1=readEntry(input," enter the name of the column with the cost data data ",temp,glFilesAttributesMap,1,sc);
				if (!valid1){
					System.out.println("Incorrect selection");
			    }
		    }			
			glFilesMainAttributesMap.put(input,temp);			
		}
	}
	
	
	/*
	 * An auxiliary method to facilitate the capturing of attributes of interest input
	 * @param input is the name of the source file being validated.
	 * @param message is the output being provided to the user. 
	 * @param accumulator is the data structure to accumulate the input received by the user.
	 * @param inputMap is the data structure being leveraged with the metadata from the configuration.
	 * @param maxEntry is the maximum number of inputs being captured from the user.
	 * @param sc is a scanner to capture user input.
	 * @return Boolean true if successful, or false if an error occurs.
	 */
	public boolean readEntry(String input, String message,List<String> accumulator, Map<String,Set<String>> inputMap,int maxEntry,Scanner sc){
		int counter =0;
		boolean result = false;
		System.out.println(message);
		System.out.println(inputMap.get(input));
		String selection = sc.nextLine();
		if (selection.isEmpty()){
			selection=sc.nextLine();
	    }
		String[] selectionarray = selection.split(" ");
		for (int i=0;i<selectionarray.length;i++){
			if (counter >= maxEntry){
				return false;
			}
			try {
				result = inputMap.get(input).contains(selectionarray[i]) && !accumulator.contains(selectionarray[i]);
			} catch(NullPointerException ex){
				return false;
			}
			if (!result){
				return false;
			}
			else {
				accumulator.add(selectionarray[i]);
				counter ++;
			}
			
		}
		return true;
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
	
	public boolean grabFilesAttributes() {	
		boolean isSuccesful=false;
		try {
			for (String input: bpaFilesMap.keySet()){
				grabFileAttributes(bpaFilesMap.get(input),bpaFilesAttributesMap);
			}
			grabGL();
			isSuccesful=true;
		} catch (RuntimeException ex) {
			isSuccesful=false;
		}
		return isSuccesful;	
	}
	
	
	public abstract String getGLFileName();
	
	public abstract boolean grabGL();
	
	public abstract void save();
	
	public abstract boolean capture(String configurationName);
	
	public abstract void save(String newName);
	
			
		
}
