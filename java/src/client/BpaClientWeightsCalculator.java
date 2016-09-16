package client;

import java.util.Map;

import bpa.BpaCostsMaker;

/**
 * @author YasserAlejandro
 * A class implementing BpaClientWeightsCalculator needs to:
 * - Provide the client weights of all BPA sources associated
 * with a particular ABC period run.
 */
public interface BpaClientWeightsCalculator {
	
	/**
	 * @return a map with the client weights of each BPA data
	 * source associated with a particular period run.
	 */
	Map<String,Map<String,Double>> getClientsWeights();
	
	/**
	 * @return a BPACostMaker associated with this BpaClientWeightsCalculator
	 */
	BpaCostsMaker getBpaCostsMaker();

}
