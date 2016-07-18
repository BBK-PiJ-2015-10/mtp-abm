package report;

import java.io.File;

public interface Report {
	
	default boolean generateReport(File srcFile,int reportchoice){
		boolean result=true;
		switch (reportchoice){
		   case 1:  {
			   ((ClientSummaryReport) this).generateReport(srcFile);
			   break;}
		   case 2:  {
			   System.out.println("Detailed Client");
			   break;}	   
		   case 3:  {
			   System.out.println("Summary Activity");
			   break;}   
		   case 4:  {
			   System.out.println("Detailed Activity");
			   break;}
		   default: {
			   System.out.println("Invalid option");
			   result=false;
			   break;}
		}     
		return result;   		  
	}

	
}
	
	
	
	


