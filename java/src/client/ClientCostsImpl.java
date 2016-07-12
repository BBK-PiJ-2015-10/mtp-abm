package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

import bpa.BpaCostsMaker;
import period.PeriodMaker;

public class ClientCostsImpl implements ClientCosts {
	
	private BpaCostsMaker bpaCostsMaker;
	
	public ClientCostsImpl(BpaCostsMaker bpaCostsMaker){
		this.bpaCostsMaker=bpaCostsMaker;
	}
	
	public Integer getActivityCost(String activityName){
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
			//System.out.println(result); 	
		} catch ( IOException | NoSuchElementException | NullPointerException ex){
			return null;
		}
		return result;
	}

	@Override
	public boolean calculateCosts() {
		// TODO Auto-generated method stub
		return false;
	}

}
