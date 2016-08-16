package bpa;

import java.io.File;

import period.PeriodMaker;

public interface BpaCostsMaker {
	
	boolean createbpaCosts();
	
	PeriodMaker getPeriodMaker();
		
	boolean createBpaCostsFile();

}
