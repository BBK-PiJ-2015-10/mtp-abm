package bpa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

import java.util.HashMap;
import java.util.Map;


public class BpaCostCalculatorImpl implements BpaCostCalculator {
	
	private BpaCostsMaker bpaCostsMaker;
	
	private Map<String,Integer> bpaCosts = new HashMap<>();
	
	public BpaCostCalculatorImpl(BpaCostsMaker bpaCostsMaker){
		this.bpaCostsMaker=bpaCostsMaker;
	}
	

	@Override
	public Integer getActivityCost(String activityName) {
		if (bpaCosts.containsKey(activityName)){
			System.out.println("This is coming from the map");
			return bpaCosts.get(activityName);
		}
		Integer result = 0;
		try (BufferedReader in = new BufferedReader(new FileReader(bpaCostsMaker.getBPACosts()));)
		{
			String line;
			in.readLine();
			while ((line = in.readLine()) != null){
				if (!line.isEmpty()) {
					String[] sentence=line.split(",");
					String key=sentence[sentence.length-1];
					String value=sentence[sentence.length-2];
					if (key.equals(activityName)){
						result=result+Integer.parseInt(value);
					}
				}
			}
		} catch ( IOException | NoSuchElementException | NullPointerException ex){
			return null;
		}
		bpaCosts.put(activityName, result);
		return result;		
	}

	
	
}
