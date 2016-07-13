package bpa;

import java.util.Map;

public interface BpaClientWeightsCalculator {
	
	Map<String,Double> getClientWeights(String bpa);

}
