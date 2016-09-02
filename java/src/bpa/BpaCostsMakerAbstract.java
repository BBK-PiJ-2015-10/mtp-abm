package bpa;

import period.PeriodMaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedReader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.NoSuchElementException;



public abstract class BpaCostsMakerAbstract implements BpaCostsMaker {
	
    //This is a reference to the Period related to this BpaCost
	protected PeriodMaker periodMaker;
	
	//This is a reference to the file that with bpaCosts
	protected File bpaCosts;
		
	public BpaCostsMakerAbstract(PeriodMaker periodMaker){
		this.periodMaker=periodMaker;
	}
	
	public PeriodMaker getPeriodMaker(){
		return this.periodMaker;
	}
	
	
	public File getBPACosts(){
		return this.bpaCosts;
	}
	
	public boolean contains(String input, String tester){
		return input.contains(tester);
	}
	
	
	//Displays the names of the files expected in the period folder
	public boolean displayInputFilesNames(){
		try {
			System.out.println("Please place on the below directory: ");
			System.out.println(periodMaker.getPeriod().getAbsolutePath());
			System.out.println("The following files: ");
			periodMaker.getConfiguration().getBPAFilesMap().keySet().forEach(System.out::println);
			if (contains(periodMaker.getConfiguration().getGLFileName(),".csv")){
				System.out.println(periodMaker.getConfiguration().getGLFileName());
			}
			System.out.println("You have 30 seconds to do so");	
		} catch (NullPointerException ex){
			return false;
		}
		return true;
	}
	
	
	//This is a timer to give x seconds to the user to place needed files on directory
	public boolean putToSleep(int microsecondstime){
		try {
			Thread.sleep(microsecondstime);
		} catch (InterruptedException ex)
		{
			return Thread.currentThread().interrupted();
		} 
		catch (IllegalArgumentException ex){
			return false;
		}
		return true;
		
	}
	
	
	/*
	 * mr is a reader that reads the glbpamapFile
	 * Values are stored in the driversMap map.
	 */
	public boolean extractGLBPAMap(Map<String,String> driversMap){
		try (BufferedReader mr = new BufferedReader
				(new FileReader(periodMaker.getConfiguration().getglbpamapFile()));)
		{
			String line;
			mr.readLine();
			while ((line = mr.readLine()) != null){
				if (!line.isEmpty()) {
					String[] sentence=line.split(",");
					String key=null;
					String value=null;
					for (int i=0;i<sentence.length;i++){
						//If string being read is not the last string in the line. Then it stores it
						//as a key. If it is the last one, it stores it as a value.
						//A key can be made out of several strings not just one.
						//The value is the BPA activity associated with the tupples put as keys
						if (i<sentence.length-1){
							if (key==null){
								key=sentence[i];
							}
							else {
								key=key+sentence[i];
							}
						}
						else {
							value=sentence[i];
						}
					}
				driversMap.put(key, value);
			}
		    periodMaker.save();
		}		
		} catch ( IOException | NoSuchElementException | NullPointerException ex){
			driversMap=null;
			return false;
		}
		return true;
	}
	
	@Override
	public boolean createBpaCostsFile(){
		try {	
		bpaCosts = new File(periodMaker.getPeriod().getAbsolutePath()+"\\"+"bpaCosts.csv");
		periodMaker.setBpaCosts(bpaCosts);
		periodMaker.save();
		} catch (NullPointerException ex){
			return false;
		}
		return true;
	}
	
	
	@Override
	public boolean createbpaCosts(Scanner sc) {
		String keyboard =null;
		try {
		displayInputFilesNames();
		putToSleep(30000);
		boolean validEntry = false;
		do {
			validEntry = validateInput(this.periodMaker.getPeriodFiles());
			if (!validEntry){
				System.out.println("Please load missing file(s), enter done when finish");
				keyboard = sc.nextLine();
				while (!keyboard.equalsIgnoreCase("done")){
					System.out.println("Invalid entry, please enter the word done when finished");
					keyboard = sc.nextLine();
				}	
			}
		} while (!validEntry);
			extractGLBPAMap(this.periodMaker.getDriversMap());
			createBpaCostsFile();
			processGL(this.periodMaker.getPeriodFiles(),this.periodMaker.getDriversMap());
		} catch (NullPointerException ex) {
			return false;
		}
		return true;
	}
	
	
	//Validate that all expected files are in place, if not, then returns false. If yes
	//it returns true.
	public abstract boolean validateInput(Map<String,File> periodFiles);
	
	
	public abstract boolean processGL(Map<String,File> periodFiles,Map<String,String> driversMap );
	

	
	

}
