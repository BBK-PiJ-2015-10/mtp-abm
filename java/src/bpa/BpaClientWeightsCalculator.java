package bpa;

import java.util.Map;

public interface BpaClientWeightsCalculator {
	
	Map<String,Map<String,Double>> getClientsWeights();
	
	BpaCostsMaker getBpaCostsMaker();

}
