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


public class ReportSummaryImplOld implements Report {
	
	private File summaryReport;
	
	private Map<String,Double> costs = new HashMap<>();
	
	public File getSummaryReportFile(){
		return this.summaryReport;
	}
	
	public Map<String,Double> getClientsCosts(){
		return this.costs;
	}
	
	public boolean createFile(File srcFile, String fileName){
		try{
			summaryReport = new File(srcFile.getParent()+"//"+fileName+".csv");
		} catch (NullPointerException ex){
			return false;
		}
		return true;
	}
	
	public boolean popMap(File srcFile, String attributefactor, String attributecost){
		
		try (BufferedReader in = new BufferedReader(new FileReader(srcFile));)
		{
			String line;
			line=in.readLine();
			String[]sentence=line.split(",");
			Integer POS1=null, POS2=null;
			for (int i=0;i<sentence.length;i++){
				if (sentence[i].equals(attributefactor)){
					POS1=i;
				}
				else if (sentence[i].equals(attributecost)){
					POS2=i;
				}
			}
			while ((line = in.readLine()) != null){
				Double totalCost;
				sentence=line.split(",");
				totalCost=Double.parseDouble(sentence[POS2]);
				String key;
				if (sentence[POS1].contains(".")){
					key=sentence[POS1].split(Pattern.quote("."))[0];
				}
				else {
					key=sentence[POS1];
				}
				if (costs.containsKey(key)){
					totalCost =costs.get(key)+totalCost;
				}
				costs.put(key,totalCost);
			}
			
		} catch ( IOException | NullPointerException | NumberFormatException | IndexOutOfBoundsException ex){
			costs.clear();
			return false;
		}
		return true;
	}
	
	public boolean popFile(String attributefactor, String attributecost){
		if (costs == null | costs.isEmpty()){
			return false;
		}
		try (
				 FileWriter fw = new FileWriter(summaryReport,false);
				 BufferedWriter bw = new BufferedWriter(fw);
				 PrintWriter out = new PrintWriter(bw);)
		{
			out.write(attributefactor+",");
			out.write(attributecost);
			out.println();
			for (String input: costs.keySet()){
				out.write(input+",");
				out.write(costs.get(input).toString());	
				out.println();
			}
		}	catch ( IOException | NullPointerException ex){	
			return false;
		}
		return true;
	}
	
	
	public boolean generateReport(File srcFile,String fileName,String attributefactor, String attributecost){
		if (!createFile(srcFile,fileName)){
			return false;
		}
		if(!popMap(srcFile,attributefactor,attributecost)){
			summaryReport=null;
			return false;
		};
		if (!popFile(attributefactor,attributecost)){
			summaryReport=null;
			costs.clear();
			return false;
		}
		return true;
	}

}
