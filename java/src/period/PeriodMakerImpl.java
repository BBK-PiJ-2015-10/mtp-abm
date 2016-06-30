package period;

import user.UserSpace;

import java.io.File;
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
	
	public UserSpace getUserSpace(){
		return this.userSpace;
	}
	
	public ConfigurationManager getConfiguration(){
		return this.configurationManager;
	}
	
	public PeriodMakerImpl(UserSpace userSpace){
		this.userSpace = userSpace;
	}
	
	public PeriodMakerImpl(File period){
		this.period=period;
	}
	
	public boolean validateConfiguration(String configName){
		return userSpace.validConfiguration(configName);
	}
	
	public void captureConfiguration(){
		Scanner sc = new Scanner(System.in);
		Boolean validEntry = false;
		String keyboardEntry;
		do {
			System.out.println("Please enter the name of the configuration that you wish to leverage");
			keyboardEntry = sc.next();
			validEntry = validateConfiguration(keyboardEntry);
			if (!validEntry){
				System.out.println("Configuration name not found");
			}
		} while (!validEntry);
		configurationManager = new ConfigurationManager(userSpace.getConfiguration(keyboardEntry));
		configurationManager.capture(keyboardEntry);		
	}
	
	public void createPeriod(){
		Scanner sc = new Scanner(System.in);
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
	public void makePeriod() {
		captureConfiguration();
		createPeriod();
	}
	
	
	@Override
	public void save(){
		try (ObjectOutputStream encode = new ObjectOutputStream(new FileOutputStream(period.getAbsolutePath()+"\\"+period.getName()+".dat"));)
		{
			encode.writeObject(period);
			encode.writeObject(userSpace);
			encode.writeObject(configurationManager);
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
			isPresent = true;
			
		} catch (ClassNotFoundException ex){
			isPresent = false;
		} catch (IOException ex2){
			isPresent = false;;
		} 
		return isPresent;		
	}
	
	
	
	

	
	
}
