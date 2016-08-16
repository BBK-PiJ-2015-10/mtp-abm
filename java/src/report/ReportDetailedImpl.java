package report;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.HashMap;


public class ReportDetailedImpl extends ReportAbstract {
	
	private List<String> attributesOtherLabels = new LinkedList<>();
	
	private Map<String,List<List<String>>> costsMap = new HashMap<>();
	
	public Map<String,List<List<String>>> getCosts(){
		return this.costsMap;
	}
	
	public List<String> getAttributesLabels(){
		return this.attributesOtherLabels;
	}
	
	public boolean alreadyInPlace(List<String> list1, List<String> list2){
		if (list1.size()==1){
			return true;
		}
		
		if (list1.get(0).equals(list2.get(0))){
			return alreadyInPlace(list1.subList(1,list1.size()),list2.subList(1,list1.size()));
		}
		else {
			return false;
		}
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
			for (int i=0;i<sentence.length;i++){
				if ((!sentence[i].equals(attributefactor)) &&(!sentence[i].equals(attributecost)) ){
					attributesOtherLabels.add(sentence[i]);
				}
			}
				
			while ((line = in.readLine()) != null){
				List<String> attributes = new LinkedList<>();
				sentence=line.split(",");
				String key;
				if (sentence[POS1].contains(".")){
					key=sentence[POS1].split(Pattern.quote("."))[0];
				}
				else {
					key=sentence[POS1];
				}
				for (int i=0;i<sentence.length;i++){
					if ((i!=POS1) && (i!=POS2)){
						if (sentence[i].contains(".")){
							attributes.add(sentence[i].split(Pattern.quote("."))[0]);
					    }
					    else {
					    	attributes.add(sentence[i]);
					    }					
					}			
				}
				attributes.add(sentence[POS2]);
				
				if (costsMap.containsKey(key)){
					boolean change=false;
					for (int m=0;m<costsMap.get(key).size();m++){
						if (alreadyInPlace(costsMap.get(key).get(m),attributes)){
							int loc = costsMap.get(key).get(m).size()-1;
							Double current=Double.valueOf(costsMap.get(key).get(m).get(loc));
							Double adder =Double.valueOf(attributes.get(loc));
							Double updated = current+adder;
							String temp1 = updated.toString();
							costsMap.get(key).get(m).remove(loc);
							costsMap.get(key).get(m).add(loc,temp1);
							change=true;
						}
					}
					
					if (!change){
						costsMap.get(key).add(attributes);
					}
					
					costsMap.get(key).sort((a,b)->a.get(0).charAt(0)-b.get(0).charAt(0));
				}
				else {
					List<List<String>> helper = new LinkedList<>();
					helper.add(attributes);
					costsMap.put(key,helper);
				}
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
			for (int i=0;i<attributesOtherLabels.size();i++){
				out.write(attributesOtherLabels.get(i)+",");
			}	
			out.write(attributecost);
			out.println();
			for (String input: costsMap.keySet()){
				for (int k=0;k<costsMap.get(input).size();k++){
					List<String> temp=new LinkedList<>();
					temp.add(input);
					for (int m=0;m<costsMap.get(input).get(k).size();m++){
						temp.add(costsMap.get(input).get(k).get(m));
					}
					for (int z=0;z<temp.size();z++){
						if (z!=temp.size()-1) {
							out.write(temp.get(z)+",");
						}
						else {
							out.write(temp.get(z));
						}
						
					}
					out.println();
				}	
			}
		}	catch ( IOException | NullPointerException ex){	
			return false;
		}
		return true;
	}
	

}
