package period;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Map;

import configuration.ConfigurationManagerSQL;
import user.UserSpace;

public class PeriodMakerImplSQL extends PeriodMakerAbstract implements Serializable {
		
	public PeriodMakerImplSQL(UserSpace userSpace){
		super(userSpace);
	}
	
	public PeriodMakerImplSQL(File period){
		super(period);
	}
	
	public void initConfigurationManager(String configName) {
		configurationManager = new ConfigurationManagerSQL(userSpace.getConfiguration(configName));
	}
		
	@Override
	public boolean capture(String periodName){
		boolean isPresent;
		try (ObjectInputStream incode = new ObjectInputStream(new FileInputStream(period.getAbsolutePath()+"\\"+periodName+".dat"));)
		{
			period = (File)incode.readObject();
			userSpace=(UserSpace)incode.readObject();
			configurationManager=(ConfigurationManagerSQL)incode.readObject();
			periodFiles=(Map<String,File>)incode.readObject();
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
