package configuration;

import java.util.Map;
import java.util.Set;
import java.util.List;

import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/*
* A CSV extension of the ConfigurationManagerAbstract. It adds a File object to reference
* a general ledger file source. Accordingly fully defines save and capture methods.
*/
public class ConfigurationManagerCSV extends ConfigurationManagerAbstract implements Serializable {
	
	private File glFile;
			
	public ConfigurationManagerCSV(File file){
		super(file);
	}
		
	public void setGLFile(String filename){
		glFile = bpaFilesMap.remove(filename);
	}
	
	public File getGLFile(){
		return glFile;
	}
	
	public String getGLFileName(){
		return glFile.getName();
	}
	
	public boolean grabGL(){
		return grabFileAttributes(glFile,glFilesAttributesMap);
	}


	public void save(){
		try (ObjectOutputStream encode = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()+"\\"+file.getName()+".dat"));)
		{
			encode.writeObject(file);
			encode.writeObject(bpaFilesMap);
			encode.writeObject(glFile);
			encode.writeObject(bpaFilesAttributesMap);
			encode.writeObject(glFilesAttributesMap);
			encode.writeObject(glFilesMainAttributesMap);
			encode.writeObject(bpaFilesMainAttributesMap);
			encode.writeObject(glbpamapFile);
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	
	public void save(String newname){
		try (ObjectOutputStream encode = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()+"\\"+newname+".dat"));)
		{
			encode.writeObject(file);
			encode.writeObject(bpaFilesMap);
			encode.writeObject(glFile);
			encode.writeObject(bpaFilesAttributesMap);
			encode.writeObject(glFilesAttributesMap);
			encode.writeObject(glFilesMainAttributesMap);
			encode.writeObject(bpaFilesMainAttributesMap);
			encode.writeObject(glbpamapFile);
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	
	public boolean capture(String configurationname){
		boolean isPresent;
		if (this.file.exists()){
			try (ObjectInputStream incode = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()+"\\"+configurationname+".dat"));)
			{
				file = (File)incode.readObject();
				bpaFilesMap=(Map<String,File>)incode.readObject();
				glFile= (File)incode.readObject();
				bpaFilesAttributesMap=(Map<String,Set<String>>)incode.readObject();
				glFilesAttributesMap=(Map<String,Set<String>>)incode.readObject();
				glFilesMainAttributesMap=(Map<String,List<String>>)incode.readObject();
				bpaFilesMainAttributesMap=(Map<String,List<String>>)incode.readObject();
				glbpamapFile=(File)incode.readObject();
				isPresent = true;
			} 
			  catch (ClassNotFoundException ex){
				System.out.println("error1");
				isPresent = false;
			} catch (IOException ex2){
				System.out.println("error2");
				isPresent = false;
			} catch (NullPointerException ex){
				System.out.println("error3");
				isPresent = false;
			}			
		}
		else {
			System.out.println("File doesn't exists");
			isPresent=false;
		}
		return isPresent;
	}
	
		
}
