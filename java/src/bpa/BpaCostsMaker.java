package bpa;

import java.util.Scanner;


import period.PeriodMaker;

public interface BpaCostsMaker {
	
	boolean createbpaCosts(Scanner sc);
	
	PeriodMaker getPeriodMaker();
		
	boolean createBpaCostsFile();

}
