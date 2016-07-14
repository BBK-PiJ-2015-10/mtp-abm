package bpa;

import java.io.File;

import period.PeriodMaker;

public interface BpaCostsMaker {
	
	boolean createbpaCosts();
	
	PeriodMaker getPeriodMaker();
	
	//File getBPACosts();
	
	//boolean createBpaCostsFile(File bpaCosts);
	
	boolean createBpaCostsFile();

}
