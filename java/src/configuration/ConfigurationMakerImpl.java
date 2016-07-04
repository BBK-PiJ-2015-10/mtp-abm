package configuration;

import java.util.Scanner;

import user.UserSpace;

import java.io.File;


public class ConfigurationMakerImpl implements ConfigurationMaker {
	
	@Override
	public void makeConfiguration(UserSpace userSpace,Scanner sc) {
		
		System.out.println("Please enter the name of the configuration you wish to create ");
		String dirname = sc.nextLine();
		String address  = userSpace.getUserSpaceFile().getAbsolutePath()+"\\";		
		File config = new File(address+dirname);
		config.mkdir();
		userSpace.addConfiguration(dirname,config);
		userSpace.save();
		System.out.println("Please go to the below location and drop your general ledger and operation data files");
		System.out.println(address+dirname);
		System.out.println("When done, please enter the word done");
		
		try {
			Thread.sleep(30000);
		} catch (InterruptedException ex)
		{
			System.out.println("Time is up");
		}
		
		String input = sc.nextLine();
		if (input.equalsIgnoreCase("done")){
			ConfigurationMapper configMapper = new ConfigurationMapperImpl(new ConfigurationManager(config));
			configMapper.mapFiles(sc);
	     }
		System.out.println("For today, you are done with the configuration set up");		
	}
	
	

	
}
