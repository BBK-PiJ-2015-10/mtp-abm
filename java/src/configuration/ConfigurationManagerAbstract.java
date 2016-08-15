package configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;

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


public abstract class ConfigurationManagerAbstract implements Serializable {

	protected File file;
	
	protected Map<String,File> bpaFilesMap = new HashMap<>();
	
	protected File glbpamapFile;
	
	protected Map<String,Set<String>> bpaFilesAttributesMap = new HashMap<>();
	
	protected Map<String,List<String>> bpaFilesMainAttributesMap = new HashMap<>();
	
	protected Map<String,Set<String>> glFilesAttributesMap = new HashMap<>();
	
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
	
	//Need to create JUnit for this
	public void setglbpamapFile(File glbpamapFile){
		this.glbpamapFile=glbpamapFile;
	}
	
	//Need to create JUnit for this
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
			result = inputMap.get(input).contains(selectionarray[i]) && !accumulator.contains(selectionarray[i]);
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
	
	public abstract String getGLFileName();
	
	public abstract boolean grabFilesAttributes();	
	
	public abstract void save();
	
	public abstract void save(String newname);
	
	public abstract boolean capture(String configurationname);
		
}
