package bpa;

import period.PeriodMaker;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class BpaCostsMakerImpl implements BpaCostsMaker {

	private PeriodMaker periodMaker;
	
	//private Map<String,File> bpaFilesMap = new HashMap<>();
	
	public BpaCostsMakerImpl(PeriodMaker periodMaker){
		this.periodMaker=periodMaker;
	}
	
	public PeriodMaker getPeriodMaker(){
		return this.periodMaker;
	}
	
	public void displayInputFilesNames(){
		System.out.println("Please place on the below directory");
		System.out.println(periodMaker.getPeriod().getAbsolutePath());
		System.out.println("The following files: ");
		periodMaker.getConfiguration().getBPAFilesMap().keySet().forEach(System.out::println);
		System.out.println(periodMaker.getConfiguration().getGLFile().getName());
		System.out.println("You have 30 seconds to do so");	
	}
	
	public void putToSleep(int microsecondstime){
		try {
			Thread.sleep(microsecondstime);
		} catch (InterruptedException ex)
		{
			System.out.println("Time is up");
		}	
	}
	
	public boolean validateInput(){
		File temp;
		for (String value :periodMaker.getConfiguration().getBPAFilesMap().keySet() ){
			temp = new File(periodMaker.getPeriod().getAbsolutePath()+"\\"+value);
			if (!temp.exists()){
				System.out.println("The file named: " +value +" is missing");
				return false;
			}
		}
		temp = new File(periodMaker.getPeriod().getAbsolutePath()+"\\"+periodMaker.getConfiguration().getGLFile().getName());
			if (!temp.exists()){
				System.out.println("The file named: " +periodMaker.getConfiguration().getGLFile().getName() +" is missing");
				return false;
			}
		return true;
	}
	
	@Override
	public void createbpaCosts() {
		displayInputFilesNames();
		putToSleep(1000);
		
		System.out.println(validateInput());
		System.out.println("You will do something soon");

		//periodMaker.getConfiguration().g
		
	}
	
	

}
