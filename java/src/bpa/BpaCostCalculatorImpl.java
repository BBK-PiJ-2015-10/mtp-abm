package bpa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.File;

import java.util.HashMap;
import java.util.Map;

/*
 * An implementation of BpaCostCalculator using a bpaCosts file
 * an internal bpaCostsMap data structure. 
 */
public class BpaCostCalculatorImpl implements BpaCostCalculator {
	
	private File bpaCosts;
		
	private Map<String,Double> bpaCostsMap = new HashMap<>();
	
	
	public BpaCostCalculatorImpl(File bpaCosts){
		this.bpaCosts=bpaCosts;
	}
	
	public Map<String,Double> getBpaCostsMap(){
		return this.bpaCostsMap;
	}
	

	@Override
	public Double getActivityCost(String activityName) {
		if (bpaCostsMap.containsKey(activityName)){
			return bpaCostsMap.get(activityName);
		}
		Double result=0.0;
		try (BufferedReader in = new BufferedReader(new FileReader(bpaCosts));)
		{
			String line;
			in.readLine();
			while ((line = in.readLine()) != null){
				if (!line.isEmpty()) {
					String[] sentence=line.split(",");
					String key=sentence[sentence.length-1];
					String value=sentence[sentence.length-2];
					if (key.equals(activityName)){
						result=result+Double.parseDouble(value);
					}
				}
			}
		} catch ( IOException | NoSuchElementException | NullPointerException ex){
			return null;
		}
		bpaCostsMap.put(activityName, result);
		return result;		
	}


	
	
}
