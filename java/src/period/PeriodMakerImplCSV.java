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

public class PeriodMakerImplCSV extends PeriodMakerAbstract implements Serializable {
		
	public PeriodMakerImplCSV(UserSpace userSpace){
		super(userSpace);
	}
	
	public PeriodMakerImplCSV(File period){
		super(period);
	}
	
	public void initConfigurationManager(String configName) {
		configurationManager = new ConfigurationManagerCSV(userSpace.getConfiguration(configName));
	}
		
	@Override
	public boolean capture(String periodName){
		boolean isPresent;
		try (ObjectInputStream incode = new ObjectInputStream(new FileInputStream(period.getAbsolutePath()+"\\"+periodName+".dat"));)
		{
			period = (File)incode.readObject();
			userSpace=(UserSpace)incode.readObject();
			configurationManager=(ConfigurationManagerCSV)incode.readObject();
			periodFiles=(Map<String,File> )incode.readObject();
			driversMap=(Map<String,String>)incode.readObject();
			bpaCosts = (File)incode.readObject();
			clientCosts = (File)incode.readObject();
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
