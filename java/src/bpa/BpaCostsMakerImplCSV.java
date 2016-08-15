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


public class BpaCostsMakerImplCSV implements BpaCostsMaker {
	
    //This is a reference to the Period related to this BpaCost
	private PeriodMaker periodMaker;
	
	//This is a reference to the file that with bpaCosts
	private File bpaCosts;
		
	public BpaCostsMakerImplCSV(PeriodMaker periodMaker){
		this.periodMaker=periodMaker;
	}
	
	public PeriodMaker getPeriodMaker(){
		return this.periodMaker;
	}
	
	
	public File getBPACosts(){
		return this.bpaCosts;
	}
	
	
	//Displays the names of the files expected in the period folder
	public boolean displayInputFilesNames(){
		try {
			System.out.println("Please place on the below directory: ");
			System.out.println(periodMaker.getPeriod().getAbsolutePath());
			System.out.println("The following files: ");
			periodMaker.getConfiguration().getBPAFilesMap().keySet().forEach(System.out::println);
			System.out.println(periodMaker.getConfiguration().getGLFileName());
			//System.out.println(periodMaker.getConfiguration().getGLFile().getName());
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
	
	//Validate that all expected files are in place, if not, then returns false. If yes
	//it returns true.
	public boolean validateInput(Map<String,File> periodFiles){
		boolean result;
		int maxFiles = periodMaker.getConfiguration().getBPAFilesMap().keySet().size()+1;
		int currFiles=0;
		File tempF;
		for (String value :periodMaker.getConfiguration().getBPAFilesMap().keySet() ){
			tempF = new File(periodMaker.getPeriod().getAbsolutePath()+"\\"+value);
			if (!tempF.exists()){
				System.out.println("The file named: " +value +" is missing");
			}
			else {
				periodFiles.put(value,tempF);
				currFiles++;
			}
		}
		String tempName = periodMaker.getPeriod().getAbsolutePath()+"\\"+periodMaker.getConfiguration().getGLFileName();
		tempF = new File(tempName);
			if (!tempF.exists()){
				System.out.println("The file named: " +tempF.getName() +" is missing");
			}
			else {
				//periodFiles.put(periodMaker.getConfiguration().getGLFile().getName(),tempF);
				periodFiles.put(periodMaker.getConfiguration().getGLFileName(),tempF);
				currFiles++;
			}
		result = (currFiles==maxFiles);
		if (!result) {
			periodFiles=null;
		}
		periodMaker.save();
		return result;
	}
	
	
	
	/*
	 * mr is a reader that reads the glbpamapFile
	 * Values are stored in the driversMap map.
	 */
	public boolean extractGLBPAMap(Map<String,String> driversMap){
		try (BufferedReader mr = new BufferedReader(new FileReader(periodMaker.getConfiguration().getglbpamapFile()));)
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
	

	/*
	* Writing to the bpaCosts file reference
	* Reading from the gl.csv file referenced on the periodFiles
	*
	*/
	public boolean extractGL(Map<String,File> periodFiles,Map<String,String> driversMap ){
		try (
				 FileWriter fw = new FileWriter(bpaCosts,false);
				 BufferedWriter bw = new BufferedWriter(fw);
				 PrintWriter out = new PrintWriter(bw);
				 BufferedReader in = new BufferedReader(new FileReader(periodFiles.get(periodMaker.getConfiguration().getGLFileName())));)
		{
			List<Integer> validPOS = new LinkedList<>();
			String line;
			String[] sentence;
			line = in.readLine();
			sentence=line.split(",");
			
			/*
			 * Checks if the sentence position being read is contained on the GLMainFileAttributes map
			 * If positive, adds the position reference into the validPOS.
			 * Gets the size of the GLMainFilesAttributes map
			 * If the sentence string, equals the last value of the glMainAtributes map, the it writes costs
			 * otherwise, it writes the label.
			 */
			for (int i=0;i<sentence.length;i++){
				if (periodMaker.getConfiguration().getGlMainFilesAttributesMap().get("gl.csv").contains(sentence[i])){
			        validPOS.add(i);
			        int listSize=periodMaker.getConfiguration().getGlMainFilesAttributesMap().get("gl.csv").size();
			        if (periodMaker.getConfiguration().getGlMainFilesAttributesMap().get("gl.csv").get(listSize-1).equals(sentence[i])){
			        	out.write("cost"+",");
			        }
			        else {
			        	out.write(sentence[i]+",");
			        }
				}
			}
			out.write("BPA");
			out.println();
			
			
			
			while ((line = in.readLine()) != null){
				
				//assigns each line being read into a sentence array
				sentence=line.split(",");
				//validPOS.get(validPOS.size()-1);
				String key = null;
				
				for (int pos: validPOS){
					//If it is not the last position. The position with the amount.
					if (pos!=validPOS.get(validPOS.size()-1)){
						//if the key is null, makes the key equal to the sentence string value
						if (key==null){
							key=sentence[pos];
						}
						//if the key is not null, adds the sentence string to the key value
						else {
							key=key+sentence[pos];
						}
					}
					//regardless it writes out the String sentence
					out.write(sentence[pos]+",");
				}
				//Looks up on the driversMap the activity associated with the key and writes it to file
				out.write(driversMap.get(key));
				out.println();
			}
			
		}	catch ( IOException | NoSuchElementException | NullPointerException ex){	
			return false;
		}
		return true;
	}
	
	
	
	@Override
	public boolean createbpaCosts() {
		try {
		displayInputFilesNames();
		putToSleep(30000);
		validateInput(this.periodMaker.getPeriodFiles());
		System.out.println(extractGLBPAMap(this.periodMaker.getDriversMap()));
		createBpaCostsFile();
		extractGL(this.periodMaker.getPeriodFiles(),this.periodMaker.getDriversMap());
		} catch (NullPointerException ex) {
			System.out.println("This is going wild from the wrong bpa costs file");
			return false;
		}
		return true;
	}
	

	
	

}
