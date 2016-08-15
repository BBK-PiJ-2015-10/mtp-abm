package period;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import configuration.ConfigurationManagerAbstract;
import configuration.ConfigurationManagerCSV;
import user.UserSpace;

public abstract class PeriodMakerAbstract implements PeriodMaker, Serializable {
	
	protected File period;
	
	protected UserSpace userSpace;
	
	protected ConfigurationManagerAbstract configurationManager;
	
	protected File bpaCosts;
	
	protected File clientCosts;
	
	//This is a test
	protected Map<String,File> periodFiles = new HashMap<>();
	
	//This is a test
	protected Map<String,String> driversMap = new HashMap<>();
	
	public File getPeriod(){
		return this.period;
	}
	
	public UserSpace getUserSpace(){
		return this.userSpace;
	}
	
	public ConfigurationManagerAbstract getConfiguration(){
		return this.configurationManager;
	}
	
	@Override
	public File getBpaCosts(){
		return this.bpaCosts;
	}
	
	@Override
	public void setBpaCosts(File bpaCosts){
		this.bpaCosts=bpaCosts;
	}
	
	@Override
	public void setClientCosts(File clientCosts){
		this.clientCosts=clientCosts;
	}
	
	@Override
	public File getClientCosts(){
		return this.clientCosts;
	}
	
	public PeriodMakerAbstract(UserSpace userSpace){
		this.userSpace = userSpace;
	}
	
	public PeriodMakerAbstract(File period){
		this.period=period;
	}
	
	//This is a test
	public Map<String,File> getPeriodFiles(){
		return this.periodFiles;
	}
	
	//This is a test
	public Map<String,String> getDriversMap(){
		return this.driversMap;
		//return null;
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
		initConfigurationManager(keyboardEntry);
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
			encode.writeObject(clientCosts);
		}
		catch (IOException ex){
			ex.printStackTrace();
		}		
	}
	
	public abstract void initConfigurationManager(String configName);
	
	public abstract boolean capture(String periodName);
	

	

}
