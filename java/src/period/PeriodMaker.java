package period;

import java.util.Scanner;

public interface PeriodMaker {

	void makePeriod(Scanner sc);
	
	//for the sake of testing
	void save();
	
	//for the sake of testing
	boolean capture(String periodName);
	
	
}
