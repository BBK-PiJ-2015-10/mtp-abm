package bpa;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class BpaClientWeightsCalculatorImpl {
	
	private BpaCostsMaker bpaCostsMaker;
	
	private Map<String,Map<String,Double>> clientsWeights = new HashMap<>();
	
	public BpaClientWeightsCalculatorImpl(BpaCostsMaker bpaCostsMaker){
		this.bpaCostsMaker=bpaCostsMaker;
	}
	
	//public 
	
	public boolean popmap(){
		
		for (String input: bpaCostsMaker.getPeriodMaker().getConfiguration().getBPAFilesMap().keySet() ){
			Map<String,Double> tempMap = new HashMap<>();
			Map<String,Double> outputMap = new HashMap<>();
			File temp=bpaCostsMaker.getPeriodMaker().getPeriodFiles().get(input);
				try (BufferedReader in = new BufferedReader(new FileReader(temp));)
				{
					String line;
					line=in.readLine();
					String[] sentence=line.split(",");
					Integer pos1=null;
					Integer pos2=null;
					for (int i=0;i<sentence.length;i++){
						  if (bpaCostsMaker.getPeriodMaker().getConfiguration().getBpaMainFilesAttributesMap().get(input).contains(sentence[i])){
							if (pos1==null){
								pos1=i;
							}
							else {
								pos2=i;
							}
						}
					}
					while ((line = in.readLine()) != null){
						if (!line.isEmpty()) {
							sentence=line.split(",");
							tempMap.put(sentence[pos1],Double.parseDouble(sentence[pos2]));
						}
					}
				} catch ( IOException | NoSuchElementException | NullPointerException ex){
					return false;
				}
				
				//System.out.println("Just populated the map for: " +input);
				Double total=tempMap.values().stream().reduce((a,b)->a+b).get();
				for (String val: tempMap.keySet()){
					outputMap.put(val,tempMap.get(val)/total);
					//System.out.println("With Client: " +val);
					//System.out.println("With value: "+tempMap.get(val)/total);
					
					
				}
				clientsWeights.put(input, outputMap);	
		}
		return true;
	}
	
	public void test(){
		bpaCostsMaker.getPeriodMaker().getConfiguration().getBpaMainFilesAttributesMap().get("phones.csv").forEach(System.out::println);
	}
	
	public void displayMap(){
		System.out.println("This is displaying the mastermap");
		for (String val: clientsWeights.keySet()){
			System.out.println("The map: " +val);
			for (String val2: clientsWeights.get(val).keySet()){
				System.out.println("With client: " +val2);
				System.out.println("With a weight of: "+clientsWeights.get(val).get(val2));
			}
			
			//for (String val2: )
			
			
		}
		
		///clientsWeights.keySet().forEach(System.out::println);
	}
	

}
