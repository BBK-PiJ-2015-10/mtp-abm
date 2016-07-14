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


public class BpaCostsMakerImpl implements BpaCostsMaker {

	private PeriodMaker periodMaker;
	
	private File bpaCosts;
		
	public BpaCostsMakerImpl(PeriodMaker periodMaker){
		this.periodMaker=periodMaker;
	}
	
	public PeriodMaker getPeriodMaker(){
		return this.periodMaker;
	}
	
	
	public File getBPACosts(){
		return this.bpaCosts;
	}
		
	public boolean displayInputFilesNames(){
		try {
			System.out.println("Please place on the below directory: ");
			System.out.println(periodMaker.getPeriod().getAbsolutePath());
			System.out.println("The following files: ");
			periodMaker.getConfiguration().getBPAFilesMap().keySet().forEach(System.out::println);
			System.out.println(periodMaker.getConfiguration().getGLFile().getName());
			System.out.println("You have 30 seconds to do so");	
		} catch (NullPointerException ex){
			return false;
		}
		return true;
	}
	
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
		String tempName = periodMaker.getPeriod().getAbsolutePath()+"\\"+periodMaker.getConfiguration().getGLFile().getName();
		tempF = new File(tempName);
			if (!tempF.exists()){
				System.out.println("The file named: " +tempF.getName() +" is missing");
			}
			else {
				periodFiles.put(periodMaker.getConfiguration().getGLFile().getName(),tempF);
				currFiles++;
			}
		result = (currFiles==maxFiles);
		if (!result) {
			periodFiles=null;
		}
		periodMaker.save();
		return result;
	}
	
	
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
		}		
		} catch ( IOException | NoSuchElementException | NullPointerException ex){
			driversMap=null;
			periodMaker.save();
			return false;
		}
		periodMaker.save();
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
	

	public boolean extractGL(Map<String,File> periodFiles,Map<String,String> driversMap ){
		try (
				 FileWriter fw = new FileWriter(bpaCosts,false);
				 BufferedWriter bw = new BufferedWriter(fw);
				 PrintWriter out = new PrintWriter(bw);
				 BufferedReader in = new BufferedReader(new FileReader(periodFiles.get(periodMaker.getConfiguration().getGLFile().getName())));)
		{
			List<Integer> validPOS = new LinkedList<>();
			String line;
			String[] sentence;
			line = in.readLine();
			sentence=line.split(",");
			
			for (int i=0;i<sentence.length;i++){
				if (periodMaker.getConfiguration().getGlMainFilesAttributesMap().get("gl.csv").contains(sentence[i])){
			        validPOS.add(i);
					out.write(sentence[i]+",");
				}
			}
			out.write("BPA");
			out.println();
			
			while ((line = in.readLine()) != null){
				
				sentence=line.split(",");
				validPOS.get(validPOS.size()-1);
				String key = null;
				for (int pos: validPOS){
					if (pos!=validPOS.get(validPOS.size()-1)){
						if (key==null){
							key=sentence[pos];
						}
						else {
							key=key+sentence[pos];
						}
					}
					out.write(sentence[pos]+",");
				}
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
		extractGLBPAMap(this.periodMaker.getDriversMap());
		createBpaCostsFile();
		extractGL(this.periodMaker.getPeriodFiles(),this.periodMaker.getDriversMap());
		} catch (NullPointerException ex) {
			return false;
		}
		return true;
	}
	

	
	

}
