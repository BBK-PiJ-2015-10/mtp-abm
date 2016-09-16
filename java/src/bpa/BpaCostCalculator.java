package bpa;

/**
 * A class that implements BpaCostCalculator provides the cost of a 
 * desired activity.
 */
public interface BpaCostCalculator {
	
	/**
	 * @param activityName whose cost is being retrieved.
	 * @return a Double value with the cost of the desired activity.
	 */
	Double getActivityCost(String activityName);
	
}
