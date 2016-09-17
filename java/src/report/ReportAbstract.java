package report;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

/*
 * An abstract class that provides the following services:
 * - Creates an external file on disk.
 * - Orchestrates the different methods needed to create a
 * report. 
 */
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
