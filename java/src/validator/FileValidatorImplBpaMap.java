package validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.NoSuchElementException;
import configuration.ConfigurationManagerAbstract;

/*
 * An implementation of FileValidator.
 */
public class FileValidatorImplBpaMap implements FileValidator {

	@Override
	public boolean validateInput(File file, String parameterName, ConfigurationManagerAbstract configMgr) {
		
		if (file.exists()){
			try (
					 BufferedReader in = new BufferedReader(new FileReader(file));)
			{
				String line;
				String[] sentence;
				Integer POS=null;
				line = in.readLine();
				sentence=line.split(",");
				for (int i=0;i<sentence.length;i++){
					if(sentence[i].equals(parameterName)){
					   POS=i;
					}
				}
				while ((line = in.readLine()) != null){
					sentence=line.split(",");
					try {
						if (!configMgr.getBpaFilesAttributesMap().containsKey(sentence[POS])){
							System.out.println(sentence[POS] +" is not a valid selection");
							return false;
						}
					} catch (NullPointerException ex){
						System.out.println("The file contains missing data input");
						return false;
					} catch (ArrayIndexOutOfBoundsException ex) {
						System.out.println("The file contains missing data input");  
						return false;
					}	
				} 	
			} 
			catch ( IOException | NoSuchElementException ex){
				System.out.println("Error on reading the provided file");
				return false;
			}
			return true;
		}
		else {
			System.out.println("File provided doesn't exist");
			return false;
		}
	}

}
