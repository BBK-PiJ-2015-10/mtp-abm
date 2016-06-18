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
		System.out.println("When done, please enter the word done");
		String input = sc.nextLine();
		if (input.equalsIgnoreCase("done")){
			ConfigurationManager configMgr = new ConfigurationManager(config);
			configMgr.scanConfig();
			System.out.println("Please enter the name and extension of the file that contains the general ledger data");
			String glName = sc.nextLine();
			configMgr.setGLFile(glName);
			configMgr.save();
	     }
		System.out.println("For today, you are done with the configuration set up");

		
	}
	
	

	
}
