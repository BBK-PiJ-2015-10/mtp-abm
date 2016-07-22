package report;

import java.io.File;

import java.util.Scanner;


public interface ReportGenerator {
		
	boolean captureChoice(Scanner sc);
		
	boolean generateReport(File srcFile,ReportAbstract report); 		  
	

	
}
	
	
	
	


