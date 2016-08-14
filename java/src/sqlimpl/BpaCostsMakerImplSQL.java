package sqlimpl;

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

import bpa.BpaCostsMaker;

import java.util.NoSuchElementException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class BpaCostsMakerImplSQL implements BpaCostsMaker {

	private PeriodMaker periodMaker;
	
	private File bpaCosts;
		
	public BpaCostsMakerImplSQL(PeriodMaker periodMaker){
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
			System.out.println(periodMaker.getConfiguration().getGLFileName());
			//System.out.println(periodMaker.getConfiguration().getGLFile().getName());
			System.out.println("You have 90 seconds to do so");	
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
		//int maxFiles = periodMaker.getConfiguration().getBPAFilesMap().keySet().size()+1;
		int maxFiles = periodMaker.getConfiguration().getBPAFilesMap().keySet().size();
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
		//String tempName = periodMaker.getPeriod().getAbsolutePath()+"\\"+periodMaker.getConfiguration().getGLFile().getName();
		//tempF = new File(tempName);
			//if (!tempF.exists()){
				//System.out.println("The file named: " +tempF.getName() +" is missing");
			//}
			//else {
				//periodFiles.put(periodMaker.getConfiguration().getGLFile().getName(),tempF);
				//periodFiles.put(periodMaker.getConfiguration().getGLFileName(),tempF);
				//currFiles++;
			//}
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
			System.out.println("The try worked");
			String line;
			mr.readLine();
			while ((line = mr.readLine()) != null){
				if (!line.isEmpty()) {
					String[] sentence=line.split(",");
					System.out.println(line);
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
			System.out.println("This sucker is not working");
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
	
	public boolean extractGL(Map<String,File> periodFiles,Map<String,String> driversMap ){
		try (
				 FileWriter fw = new FileWriter(bpaCosts,false);
				 BufferedWriter bw = new BufferedWriter(fw);
				 PrintWriter out = new PrintWriter(bw);)
				//PrintWriter out = new PrintWriter(bw);
				 //BufferedReader in = new BufferedReader(new FileReader(periodFiles.get(periodMaker.getConfiguration().getGLFile().getName())));)
		{
			List<Integer> validPOS = new LinkedList<>();
			//String line;
			//String[] sentence;
			Statement st = null;
			ResultSet rs = null;
			//line = in.readLine();
			//sentence=line.split(",");
			try {
				//The first legs create a connection and creates a statement
				st = ((ConfigurationManagerSQL)periodMaker.getConfiguration()).getGLConnection().createStatement();
				//Executes a Query. Selects all columns from file that is stored as the last element name of the Connection setting list. The table name.
				rs = st.executeQuery("SELECT * FROM `"+((ConfigurationManagerSQL)periodMaker.getConfiguration()).getGLConnectionSettings().get(((ConfigurationManagerSQL)periodMaker.getConfiguration()).getGLConnectionSettings().size()-1)+"`");
				//Determine the valid position to be used in the retrieve the data from the SQL file. It starts with 1 instead of 0.
				for (int i=1;i<=rs.getMetaData().getColumnCount();i++){
					//If the column name in position i is part of the GLMainFileAttributes map, then it is added to the validPOS list.
					if (periodMaker.getConfiguration().getGlMainFilesAttributesMap().get(periodMaker.getConfiguration().getGLFileName()).contains(rs.getMetaData().getColumnName(i))){
						validPOS.add(i);
						//This captures the number of elements of the GLMainFilesAttributes map
						int listSize=periodMaker.getConfiguration().getGlMainFilesAttributesMap().get(periodMaker.getConfiguration().getGLFileName()).size();
						//If it is the last element, it writes cost.
						if (periodMaker.getConfiguration().getGlMainFilesAttributesMap().get(periodMaker.getConfiguration().getGLFileName()).get(listSize-1).equals(rs.getMetaData().getColumnName(i))){
				        	out.write("cost"+",");
				        }
						//otherwise it write the column name
				        else {
				        	out.write(rs.getMetaData().getColumnName(i)+",");
				        }
					}
				}	
			} catch (NoSuchElementException | IllegalStateException | SQLException ex){
				return false;
			}
			
			
			//for (int i=0;i<sentence.length;i++){
				//if (periodMaker.getConfiguration().getGlMainFilesAttributesMap().get("gl.csv").contains(sentence[i])){
			        //validPOS.add(i);
			        //int listSize=periodMaker.getConfiguration().getGlMainFilesAttributesMap().get("gl.csv").size();
			        //if (periodMaker.getConfiguration().getGlMainFilesAttributesMap().get("gl.csv").get(listSize-1).equals(sentence[i])){
			        	//out.write("cost"+",");
			        //}
			        //else {
			        	//out.write(sentence[i]+",");
			        //}
				//}
			//}
			out.write("BPA");
			out.println();
			
			
			
			
			try {
			while (rs.next()){
				//sentence=line.split(",");
				//validPOS.get(validPOS.size()-1);
				String key = null;
				for (int pos: validPOS){
					if (pos!=validPOS.get(validPOS.size()-1)){
						if (key==null){
							//key=sentence[pos];
							key=rs.getString(pos);
						}
						else {
							//key=key+sentence[pos];
							key=key+rs.getString(pos);
						}
						out.write(rs.getString(pos)+",");
					}
					else {
						Double dbl = rs.getDouble(pos);
						out.write(dbl.toString()+",");
					}
					//out.write(sentence[pos]+",");
				}
				out.write(driversMap.get(key));
				
				out.println();
			}	
			} catch (SQLException ex){
					ex.printStackTrace();
			}
			
			
			
			
			
			//while ((line = in.readLine()) != null){
				
				//sentence=line.split(",");
				//validPOS.get(validPOS.size()-1);
				//String key = null;
				//for (int pos: validPOS){
					//if (pos!=validPOS.get(validPOS.size()-1)){
						//if (key==null){
							//key=sentence[pos];
						//}
						//else {
							//key=key+sentence[pos];
						//}
					//}
					//out.write(sentence[pos]+",");
				//}
				//out.write(driversMap.get(key));
				//out.println();
			//}
			
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
