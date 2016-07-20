package report;

import java.io.File;


public interface ReportGenerator {
	
	
	static boolean generateReport(File srcFile,int reportchoice,ReportAbstract report){
		boolean result=true;
		switch (reportchoice){
		   case 1:  {
			   report = new ReportSummaryImpl();
			   report.generateReport(srcFile,"reportSummaryClient","client","cost");
			   break;}
		   case 2:  {
			   report = new ReportSummaryImpl();
			   report.generateReport(srcFile,"reportSummaryBPA","BPA","cost");
			   break;}	   
		   case 3:  {
			   report = new ReportDetailedImpl();
			   report.generateReport(srcFile,"reportDetailedBPA","BPA","Amount");
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
	
	
	
	


