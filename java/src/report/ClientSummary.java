package report;

import java.io.File;

import java.util.Map;
import java.util.HashMap;


public class ClientSummary implements Report {
	
	private File clientSummaryReport;
	
	private Map<String,Double> clientCosts = new HashMap<>();
	
	public boolean createFile(File srcFile){
		try{
			clientSummaryReport = new File(srcFile.getParent()+"//"+"clientSummary.csv");
		} catch (NullPointerException ex){
			return false;
		}
		return true;
	}
	
	public boolean popMap(){
		
		
		return true;
	}
	
	
	public boolean generateReport(File srcFile){
		
		createFile(srcFile);
		
		//System.out.println(srcFile.getParent());
		System.out.println("This is just a sample");
		return true;
	}

}
