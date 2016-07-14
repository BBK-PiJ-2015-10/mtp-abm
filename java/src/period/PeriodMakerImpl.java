package period;

import user.UserSpace;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import configuration.ConfigurationManager;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;



public class PeriodMakerImpl implements PeriodMaker, Serializable {
	
	private File period;
	
	private UserSpace userSpace;
	
	private ConfigurationManager configurationManager;
	
	private File bpaCosts;
	
	//This is a test
	private Map<String,File> periodFiles = new HashMap<>();
	
	//This is a test
	private Map<String,String> driversMap = new HashMap<>();
	
	public File getPeriod(){
		return this.period;
	}
	
	public UserSpace getUserSpace(){
		return this.userSpace;
	}
	
	public ConfigurationManager getConfiguration(){
		return this.configurationManager;
	}
	
	@Override
	public File getBpaCosts(){
		return this.bpaCosts;
	}
	
	public void setBpaCosts(File bpaCosts){
		this.bpaCosts=bpaCosts;
	}
	
	
	
	
	public PeriodMakerImpl(UserSpace userSpace){
		this.userSpace = userSpace;
	}
	
	public PeriodMakerImpl(File period){
		this.period=period;
	}
	
	//This is a test
	public Map<String,File> getPeriodFiles(){
		return this.periodFiles;
	}
	
	//This is a test
	public Map<String,String> getDriversMap(){
		return this.driversMap;
	}
	
	public boolean validateConfiguration(String configName){
		return userSpace.validConfiguration(configName);
	}
	
	public boolean captureConfiguration(Scanner sc){
		Boolean validEntry = false;
		String keyboardEntry;
		do {
			System.out.println("Please enter the name of the configuration that you wish to leverage");
			keyboardEntry = sc.nextLine();
			validEntry = validateConfiguration(keyboardEntry);
			if (!validEntry){
				System.out.println("Configuration name not found");
			}
		} while (!validEntry);
		configurationManager = new ConfigurationManager(userSpace.getConfiguration(keyboardEntry));
		configurationManager.capture(keyboardEntry);
		return validEntry;
	}
	
	public void createPeriod(Scanner sc){
		String dirname;
		System.out.println("Please enter the name of the period you wish to create ");
		dirname = sc.nextLine();
		String address  = userSpace.getUserSpaceFile().getAbsolutePath()+"\\";		
		period = new File(address+dirname);
		period.mkdir();
		userSpace.addPeriod(dirname,period);
		userSpace.save();
	}

	@Override
	public void makePeriod(Scanner sc) {
		captureConfiguration(sc);
		createPeriod(sc);
	}
	
	
	
	
	@Override
	public void save(){
		try (ObjectOutputStream encode = new ObjectOutputStream(new FileOutputStream(period.getAbsolutePath()+"\\"+period.getName()+".dat"));)
		{
			encode.writeObject(period);
			encode.writeObject(userSpace);
			encode.writeObject(configurationManager);
			encode.writeObject(periodFiles);
			encode.writeObject(driversMap);
			encode.writeObject(bpaCosts);
		}
		catch (IOException ex){
			ex.printStackTrace();
		}		
	}
	
	
	@Override
	public boolean capture(String periodName){
		boolean isPresent;
		try (ObjectInputStream incode = new ObjectInputStream(new FileInputStream(period.getAbsolutePath()+"\\"+periodName+".dat"));)
		{
			period = (File)incode.readObject();
			userSpace=(UserSpace)incode.readObject();
			configurationManager=(ConfigurationManager)incode.readObject();
			periodFiles=(Map<String,File> )incode.readObject();
			driversMap=(Map<String,String>)incode.readObject();
			bpaCosts = (File)incode.readObject();
			isPresent = true;
			
		} catch (ClassNotFoundException ex){
			System.out.println("error1");
			isPresent = false;
			
		} catch (IOException ex2){
			System.out.println("error2");
			isPresent = false;
			
		} 
		return isPresent;		
	}
	
	
	
	

	
	
}
