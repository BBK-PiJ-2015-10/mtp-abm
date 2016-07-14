package client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import bpa.BpaCostCalculator;
import bpa.BpaClientWeightsCalculator;


public class ClientCostsImpl implements ClientCosts {
	
	private BpaCostCalculator bpaCostCalculator;
	
	private BpaClientWeightsCalculator bpaClientWeightsCalculator;
	
	private File clientCosts;
	
	public ClientCostsImpl(BpaCostCalculator bpaCostCalculator,BpaClientWeightsCalculator bpaClientWeightsCalculator){
		this.bpaCostCalculator=bpaCostCalculator;
		this.bpaClientWeightsCalculator=bpaClientWeightsCalculator;
	}
	
	public boolean createClientCostsFile(){
		try {
			//clientCosts = new File(bpaCostCalculator.getAddress()+"\\"+"clientCosts.csv");
			clientCosts = new File(bpaClientWeightsCalculator.getBpaCostsMaker().getPeriodMaker().getPeriod().getAbsolutePath()+"\\"+"clientCosts.csv");
			} catch (NullPointerException ex){
				return false;
			}
			return true;
	}
	
	@Override
	public boolean calculateClientCosts() {
		createClientCostsFile();
		try (
				 FileWriter fw = new FileWriter(clientCosts,false);
				 BufferedWriter bw = new BufferedWriter(fw);
				 PrintWriter out = new PrintWriter(bw);)
		{
			for (String driver: bpaClientWeightsCalculator.getClientsWeights().keySet()){
				for (String client: bpaClientWeightsCalculator.getClientsWeights().get(driver).keySet()){
					List<String> sentence = new LinkedList<>();
					sentence.add(client);
					sentence.add(driver);
					double clientdrivercost = bpaClientWeightsCalculator.getClientsWeights().get(driver).get(client) * bpaCostCalculator.getActivityCost(driver);
					sentence.add(String.valueOf(clientdrivercost));
					for (int i=0;i<sentence.size();i++){
						if (i<sentence.size()-1){
							out.write(sentence.get(i)+",");
						}
						else {
							out.write(sentence.get(i));
						}
					}
					out.println();
				}
			}
		}	catch ( IOException | NoSuchElementException | NullPointerException ex){	
			return false;
		}
		return true;
	}

}
