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


public class BpaSummaryReport implements Report {
	
	private File bpaSummaryReport;
	
	private Map<String,Double> bpaCosts = new HashMap<>();
	
	public File getBpaSummaryReportFile(){
		return this.bpaSummaryReport;
	}
	
	public Map<String,Double> getClientsCosts(){
		return this.bpaCosts;
	}
	
	public boolean createFile(File srcFile){
		try{
			bpaSummaryReport = new File(srcFile.getParent()+"//"+"bpaSummary.csv");
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
				if (bpaCosts.containsKey(sentence[POS1])){
					totalCost =bpaCosts.get(sentence[POS1])+totalCost;
				}
				bpaCosts.put(sentence[POS1],totalCost);
			}
			
		} catch ( IOException | NullPointerException | NumberFormatException | IndexOutOfBoundsException ex){
			bpaCosts.clear();
			return false;
		}
		return true;
	}
	
	public boolean popFile(String attributefactor, String attributecost){
		if (bpaCosts == null | bpaCosts.isEmpty()){
			return false;
		}
		try (
				 FileWriter fw = new FileWriter(bpaSummaryReport,false);
				 BufferedWriter bw = new BufferedWriter(fw);
				 PrintWriter out = new PrintWriter(bw);)
		{
			out.write(attributefactor+",");
			out.write("attributecost");
			out.println();
			for (String input: bpaCosts.keySet()){
				out.write(input+",");
				out.write(bpaCosts.get(input).toString());	
				out.println();
			}
		}	catch ( IOException | NullPointerException ex){	
			return false;
		}
		return true;
	}
	
	
	public boolean generateReport(File srcFile,String attributefactor, String attributecost){
		if (!createFile(srcFile)){
			return false;
		}
		if(!popMap(srcFile,attributefactor,attributecost)){
			bpaSummaryReport=null;
			return false;
		};
		if (!popFile(attributefactor,attributecost)){
			bpaSummaryReport=null;
			bpaCosts.clear();
			return false;
		}
		return true;
	}

}
