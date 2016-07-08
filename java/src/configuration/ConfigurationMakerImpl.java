package configuration;

import java.util.Scanner;

import user.UserSpace;

import java.io.File;


public class ConfigurationMakerImpl implements ConfigurationMaker {
		
	public ConfigurationMakerImpl(){	
	}
	
	
	public String captureInput(String message, Scanner sc){
		System.out.println(message);
		return sc.nextLine();
	}
	
	
	@Override
	public boolean makeConfiguration(UserSpace userSpace, Scanner sc) {
		if (sc == null || userSpace == null){
			return false;
		}
		String dirname = captureInput("Please enter the name of the configuration you wish to create ",sc);
		if (dirname.isEmpty()){
			return false;
		}		
		String address  = userSpace.getUserSpaceFile().getAbsolutePath()+"\\";		
		File config = new File(address+dirname);
		config.mkdir();
		userSpace.addConfiguration(dirname,config);
		userSpace.save();
		System.out.println("Please go to the below location and drop your general ledger and operation data files");
		System.out.println(address+dirname);
		System.out.println("When done, please enter the word done");
			
		try {
			Thread.sleep(20000);
		} catch (InterruptedException ex)
		{
			System.out.println("Time is up");
		}
		
		try {
		String input = sc.nextLine();
		if (input.equalsIgnoreCase("done")){
			ConfigurationMapper configMapper = new ConfigurationMapperImpl(new ConfigurationManager(config));
			configMapper.mapFiles(sc);
	     }
		System.out.println("For today, you are done with the configuration set up");
		return true;
		} catch (NullPointerException ex){
			userSpace.removeConfiguration(dirname);
			userSpace.save();
			String tempD = config.getAbsolutePath()+"//"+config.getName()+".dat";
			new File(tempD).delete();
			String tempM = config.getAbsolutePath()+"//"+"glbpamap.csv";
			File tempMF = new File(tempM);
			tempMF.delete();
			config.delete();
			return false;
		}
	}
	
	
	

	
}
