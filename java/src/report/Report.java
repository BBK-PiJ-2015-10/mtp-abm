package report;

import java.io.File;



public interface Report {
	
	default boolean generateReport(File srcFile,int reportchoice){
		boolean result=true;
		switch (reportchoice){
		   case 1:  {
			   ReportAbstract type1 = new ReportSummaryImpl();
			   type1.generateReport(srcFile,"reportSummaryClient","client","cost");
			   break;}
		   case 2:  {
			   ReportAbstract type2 = new ReportSummaryImpl();
			   type2.generateReport(srcFile,"reportSummaryBPA","BPA","cost");
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
	
	
	
	


