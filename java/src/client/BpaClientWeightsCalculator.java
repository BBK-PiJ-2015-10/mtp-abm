package client;

import java.util.Map;

import bpa.BpaCostsMaker;

public interface BpaClientWeightsCalculator {
	
	Map<String,Map<String,Double>> getClientsWeights();
	
	BpaCostsMaker getBpaCostsMaker();

}
