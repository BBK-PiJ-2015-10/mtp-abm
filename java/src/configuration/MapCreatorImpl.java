package configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class MapCreatorImpl implements MapCreator {

	@Override
	public void createMap(ConfigurationManager configurationManager) {
			
		File glbpamapFile = new File(configurationManager.getFile().getAbsolutePath()+"\\"+"glbpamap.csv");
		
		try {
			glbpamapFile.createNewFile();
		}
		catch (IOException ex) {
			System.out.println("Something went off");
		}
			
		try (
				FileWriter fw = new FileWriter(glbpamapFile,false);
				 BufferedWriter bw = new BufferedWriter(fw);
				 PrintWriter out = new PrintWriter(bw);	
				 Scanner scanner = new Scanner(new FileReader(configurationManager.getGLFile()));)
		{
			scanner.close();
			out.close();
		} catch (FileNotFoundException ex){
			ex.printStackTrace();
		} catch (IOException ex){
			ex.printStackTrace();
		}
		
		
	}

}
