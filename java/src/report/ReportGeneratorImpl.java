package report;

import java.io.File;

import java.util.Scanner;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

import java.lang.IllegalArgumentException;



public class ReportGeneratorImpl implements ReportGenerator {
	
		
	private List<String> optionsList;
	
	private Map<Integer,String> optionsMap;
	
	private Integer choice =null;
	
	public ReportGeneratorImpl(){
		initialize();
	}
	
	public boolean initialize(){
		optionsList = new LinkedList();
		optionsList = Arrays.asList("SummaryClientReport","DetailedClientReport","SummaryBPAReport","DetailedBPAReport");
		optionsMap = new HashMap();
		for (int i=0;i<optionsList.size();i++){
			optionsMap.put(i+1,optionsList.get(i));
		}	
		return true;
	}
	
	
	public boolean presentChoices(){
		for (int i=1;i<=optionsMap.size();i++){
			System.out.println("For: " +optionsMap.get(i) +"," +" type: " +i);
		}
		return true;
	}
	
	
	@Override
	public boolean captureChoice(Scanner sc){
		if (!presentChoices()){
			return false;
		}
		boolean validEntry = false;
		while (!validEntry){
			try {
				choice = Integer.parseInt(sc.nextLine());
				if (optionsMap.containsKey(choice)){
					validEntry=true;
				}
				else {
					throw new IllegalArgumentException();
				}
			} catch ( NullPointerException | IllegalArgumentException ex){
				System.out.println("Invalid entry, please enter a valid selection");
			}
		}
		System.out.println("You have entered" +choice);
		return validEntry;
	}
	
	
	
	@Override
	public boolean generateReport(File srcFile,ReportAbstract report){
		boolean result=true;
		switch (choice){
		   case 1:  {
			   report = new ReportSummaryImpl();
			   report.generateReport(srcFile,"reportSummaryClient","client","cost");
			   break;}
		   case 2:  {
			   report = new ReportDetailedImpl();
			   report.generateReport(srcFile,"reportDetailedClient","client","cost");
			   break;}		    
		   case 3:  {
			   report = new ReportSummaryImpl();
			   report.generateReport(srcFile,"reportSummaryBPA","BPA","Amount");
			   break;}	   
		   case 4:  {
			   report = new ReportDetailedImpl();
			   report.generateReport(srcFile,"reportDetailedBPA","BPA","Amount");
			   break;}
		   default: {
			   System.out.println("Invalid option");
			   result=false;
			   break;}
		}     
		return result;   		  
	}	

}
