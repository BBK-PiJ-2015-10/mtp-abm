package report;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.HashMap;


public abstract class ReportAbstract  {
	
	protected File reportFile;
	
	protected Map<?,?> costsMap = new HashMap<>();
		
	public File getReportFile(){
		return this.reportFile;
	}
	
	public Map<?,?> getCosts(){
		return this.costsMap;
	}
	
	public boolean createFile(File srcFile, String fileName){
		try{
			reportFile = new File(srcFile.getParent()+"//"+fileName+".csv");
		} catch (NullPointerException ex){
			return false;
		}
		return true;
	}
	
	public abstract boolean popMap(File srcFile, String attributefactor, String attributecost);
	
	public abstract boolean popFile(String attributefactor, String attributecost);
		
	public boolean generateReport(File srcFile,String fileName,String attributefactor, String attributecost){
		if (!createFile(srcFile,fileName)){
			return false;
		}
		if(!popMap(srcFile,attributefactor,attributecost)){
			reportFile=null;
			return false;
		};
		if (!popFile(attributefactor,attributecost)){
			reportFile=null;
			costsMap.clear();
			return false;
		}
		return true;
	}

}
