package period;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import configuration.ConfigurationManagerAbstract;
import user.UserSpace;

/*
 * An abstract implementation of PeriodMaker based on a directory creating 
 * within a user's disk space. 
 */
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
	}
	
	public boolean validateConfiguration(String configName){
		return userSpace.validConfiguration(configName);
	}
	
	
	public String captureConfiguration(Scanner sc,String configName){
		initConfigurationManager(configName);
		configurationManager.capture(configName);
		return configName;
	}
	
	
	public void createPeriod(Scanner sc,String configName){
		String dirname;
		System.out.println("Please enter the name of the period you wish to create ");
		dirname = sc.nextLine();
		String address  = userSpace.getUserSpaceFile().getAbsolutePath()+"\\";		
		period = new File(address+dirname);
		period.mkdir();
		userSpace.addPeriod(dirname,period,configName);
		userSpace.save();
	}

	@Override
	public void makePeriod(Scanner sc,String configName){
		createPeriod(sc,captureConfiguration(sc,configName));
	}
	
		
	public abstract void save();
	
	public abstract void initConfigurationManager(String configName);
	
	public abstract boolean capture(String periodName);
	

	

}
