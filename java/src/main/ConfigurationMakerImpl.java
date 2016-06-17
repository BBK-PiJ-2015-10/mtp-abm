package main;

import java.util.Scanner;
import java.io.File;


public class ConfigurationMakerImpl implements ConfigurationMaker {
	
	@Override
	public void makeConfiguration(UserSpace userSpace) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the name of the configuration you wish to create ");
		String dirname = sc.nextLine();
		String address  = userSpace.getUserSpaceFile().getAbsolutePath()+"\\";		
		File config = new File(address+dirname);
		config.mkdir();
		userSpace.addConfiguration(dirname,config);
		userSpace.save();
		System.out.println("Please go to the below location and drop your general ledger and operation data files");
		System.out.println(address+dirname);

		
	}
	
	

	
}
