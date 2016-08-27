package client;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import bpa.BpaCostsMaker;

public class BpaClientWeightsCalculatorImpl implements BpaClientWeightsCalculator {
	
    private BpaCostsMaker bpaCostsMaker;
	
	private Map<String,Map<String,Double>> clientsWeights = new HashMap<>();
	
	public BpaClientWeightsCalculatorImpl(BpaCostsMaker bpaCostsMaker){
		this.bpaCostsMaker=bpaCostsMaker;
	}
	
	public BpaCostsMaker getBpaCostsMaker(){
		return this.bpaCostsMaker;
	}
	
	public Map<String,Map<String,Double>> getClientsWeights(){
		try {
		if (clientsWeights.isEmpty()){
			if(!calcWeights()){
				return null;
			}
		}
		return this.clientsWeights;
		} catch (NullPointerException ex){
			return null;
		}
	}
	
	public boolean calcWeights(){
		
		try {
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
								if (tempMap.containsKey(sentence[pos1])){
									tempMap.put(sentence[pos1],tempMap.get(sentence[pos1])+Double.parseDouble(sentence[pos2]));
								}
								else {
									tempMap.put(sentence[pos1],Double.parseDouble(sentence[pos2]));	
								}
							}
						}
					} catch ( IOException | NoSuchElementException | NullPointerException ex){
						return false;
					}
						Double total=tempMap.values().stream().reduce((a,b)->a+b).get();
						for (String val: tempMap.keySet()){
							outputMap.put(val,tempMap.get(val)/total);
						}
						clientsWeights.put(input, outputMap);
			}
			return true;
		} catch (NullPointerException ex){
			return false;
		}
	}
		

}
