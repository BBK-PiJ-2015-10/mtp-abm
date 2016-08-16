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

import configuration.ConfigurationManagerSQL;

import java.util.NoSuchElementException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class BpaCostsMakerImplSQL extends BpaCostsMakerAbstract {
		
	public BpaCostsMakerImplSQL(PeriodMaker periodMaker){
		super(periodMaker);
	}
	
	
	public boolean validateInput(Map<String,File> periodFiles){
		boolean result;
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
		result = (currFiles==maxFiles);
		if (!result) {
			periodFiles=null;
		}
		periodMaker.save();
		return result;
	}
	
	
	public boolean extractGL(Map<String,File> periodFiles,Map<String,String> driversMap ){
		try (
				 FileWriter fw = new FileWriter(bpaCosts,false);
				 BufferedWriter bw = new BufferedWriter(fw);
				 PrintWriter out = new PrintWriter(bw);)
		{
			List<Integer> validPOS = new LinkedList<>();
			Statement st = null;
			ResultSet rs = null;
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
			out.write("BPA");
			out.println();
			try {
			while (rs.next()){
				String key = null;
				for (int pos: validPOS){
					if (pos!=validPOS.get(validPOS.size()-1)){
						if (key==null){
							key=rs.getString(pos);
						}
						else {
							key=key+rs.getString(pos);
						}
						out.write(rs.getString(pos)+",");
					}
					else {
						Double dbl = rs.getDouble(pos);
						out.write(dbl.toString()+",");
					}
				}
				out.write(driversMap.get(key));
				
				out.println();
			}	
			} catch (SQLException ex){
					ex.printStackTrace();
			}
		}	catch ( IOException | NoSuchElementException | NullPointerException ex){	
			return false;
		}
		return true;
	}
	

	

}
