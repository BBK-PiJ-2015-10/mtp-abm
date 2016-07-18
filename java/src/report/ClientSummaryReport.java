package report;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.Map;
import java.util.HashMap;


public class ClientSummaryReport implements Report {
	
	private File clientSummaryReport;
	
	private Map<String,Double> clientCosts = new HashMap<>();
	
	public File getClientSummaryReportFile(){
		return this.clientSummaryReport;
	}
	
	public Map<String,Double> getClientsCosts(){
		return this.clientCosts;
	}
	
	public boolean createFile(File srcFile){
		try{
			clientSummaryReport = new File(srcFile.getParent()+"//"+"clientSummary.csv");
		} catch (NullPointerException ex){
			return false;
		}
		return true;
	}
	
	public boolean popMap(File srcFile){
		
		try (BufferedReader in = new BufferedReader(new FileReader(srcFile));)
		{
			String line;
			line=in.readLine();
			String[]sentence;
			while ((line = in.readLine()) != null){
				Double totalCost;
				sentence=line.split(",");
				totalCost=Double.parseDouble(sentence[2]);
				if (clientCosts.containsKey(sentence[0])){
					totalCost =clientCosts.get(sentence[0])+totalCost;
				}
				clientCosts.put(sentence[0],totalCost);
			}
			
		} catch ( IOException | NullPointerException | NumberFormatException | IndexOutOfBoundsException ex){
			clientCosts.clear();
			return false;
		}
		return true;
	}
	
	public boolean popFile(){
		if (clientCosts == null | clientCosts.isEmpty()){
			return false;
		}
		try (
				 FileWriter fw = new FileWriter(clientSummaryReport,false);
				 BufferedWriter bw = new BufferedWriter(fw);
				 PrintWriter out = new PrintWriter(bw);)
		{
			out.write("client"+",");
			out.write("cost");
			out.println();
			for (String driver: clientCosts.keySet()){
				out.write(driver+",");
				out.write(clientCosts.get(driver).toString());	
				out.println();
			}
		}	catch ( IOException | NullPointerException ex){	
			return false;
		}
		return true;
	}
	
	
	public boolean generateReport(File srcFile){
		if (!createFile(srcFile)){
			return false;
		}
		if(!popMap(srcFile)){
			return false;
		};
		if (!popFile()){
			return false;
		}
		return true;
	}

}
