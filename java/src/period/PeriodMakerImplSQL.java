package period;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

import configuration.ConfigurationManagerSQL;
import configuration.ConfigurationManagerAbstract;
import configuration.ConfigurationManagerCSV;
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
			isPresent = false;
		} catch (IOException ex2){
			isPresent = false;
		} 
		return isPresent;		
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
	
	
	

}
