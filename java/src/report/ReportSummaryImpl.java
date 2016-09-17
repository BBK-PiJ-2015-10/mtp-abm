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

/*
 * An extension of the ReportAbstract class. It fully defines
 * abstract methods to provide a Summary type of an ABC report.
 */
public class ReportSummaryImpl extends ReportAbstract {
	
	private Map<String,Double> costsMap = new HashMap<>();
	
	public Map<String,Double> getClientsCosts(){
		return this.costsMap;
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
				if (costsMap.containsKey(key)){
					totalCost =costsMap.get(key)+totalCost;
				}
				costsMap.put(key,totalCost);
			}
			
		} catch ( IOException | NullPointerException | NumberFormatException | IndexOutOfBoundsException ex){
			costsMap.clear();
			return false;
		}
		return true;
	}
	
	public boolean popFile(String attributefactor, String attributecost){
		if (costsMap == null | costsMap.isEmpty()){
			return false;
		}
		try (
				 FileWriter fw = new FileWriter(reportFile,false);
				 BufferedWriter bw = new BufferedWriter(fw);
				 PrintWriter out = new PrintWriter(bw);)
		{
			out.write(attributefactor+",");
			out.write(attributecost);
			out.println();
			for (String input: costsMap.keySet()){
				out.write(input+",");
				out.write(costsMap.get(input).toString());	
				out.println();
			}
		}	catch ( IOException | NullPointerException ex){	
			return false;
		}
		return true;
	}
	
	

}
