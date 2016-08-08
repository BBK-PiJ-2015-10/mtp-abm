package configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedReader;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

import validator.FileValidator;
import validator.FileValidatorImpl;

public class MapCreatorImpl implements MapCreator {
	
	private boolean manualFlag;
	
	private FileValidator fileValidator = new FileValidatorImpl();
		
	public MapCreatorImpl(boolean manualFlag){
		this.manualFlag=manualFlag;
	}

	@Override
	public boolean createMap(ConfigurationManager configurationManager, Scanner keyboard, String mapName) {
		
		if ((keyboard==null) || (mapName == null) || (configurationManager == null) ){	
			return false;
		}	
		else {
			
			File glbpamapFile = new File(configurationManager.getFile().getAbsolutePath()+"\\"+mapName);
			
			try {
				glbpamapFile.createNewFile();
			}
			catch (IOException ex) {
				System.out.println("Couldn't create the file");
				return false;
			}
			
		
			try (
					 FileWriter fw = new FileWriter(glbpamapFile,false);
					 BufferedWriter bw = new BufferedWriter(fw);
					 PrintWriter out = new PrintWriter(bw);
					 BufferedReader in = new BufferedReader(new FileReader(configurationManager.getGLFile()));)
			{
				int size = configurationManager.getGlMainFilesAttributesMap().
						get(configurationManager.getGLFileName()).size();
						//get(configurationManager.getGLFile().getName()).size();
				List<String> attri = configurationManager.getGlMainFilesAttributesMap().
						get(configurationManager.getGLFileName()).subList(0, size-1);
						//get(configurationManager.getGLFile().getName()).subList(0, size-1);
				List<Integer> attripos = new LinkedList<>();
				for (int i=0;i<attri.size();i++){
					out.write(attri.get(i)+",");
				}
				out.write("BPA");
				out.println();
				String line;
				String[] sentence;
				line = in.readLine();
				sentence=line.split(",");
				for (int i=0;i<sentence.length;i++){
					if(attri.contains(sentence[i])){
						attripos.add(i);
					}
				}
				Set<String> attriset = new HashSet<>();
				while ((line = in.readLine()) != null){
					sentence=line.split(",");
						String bpaDriver=null;
					String longword=null;
					boolean validEntry = false;
					for (Integer position : attripos){
						try {
							longword.isEmpty();
							longword =longword+" "+sentence[position];
						}	
						catch (NullPointerException ex){
							longword = sentence[position];
						}
					}
					if (!attriset.contains(longword)){
						attriset.add(longword);
						if (!manualFlag){
							do {
								System.out.println("For: "+longword);
								System.out.println("Type the full name of the file with the driver data for");
								bpaDriver = keyboard.nextLine();
								validEntry = configurationManager.getBpaFilesAttributesMap().containsKey(bpaDriver);
								if (!validEntry){
									System.out.println("File named entry doesn't exist");
								}
							} while (!validEntry);
						}	
						for (Integer position : attripos){
							out.write(sentence[position]+",");
						}
						if (!manualFlag){
							out.write(bpaDriver);
						}
					}
					out.println();
				}
			} catch ( IOException | NoSuchElementException ex){
				glbpamapFile.delete();
				return false;
			}
			configurationManager.setglbpamapFile(glbpamapFile);
			configurationManager.save();
			if (manualFlag){
				System.out.println("Please go to :  " +configurationManager.getFile().getAbsolutePath());
				System.out.println("open file " +glbpamapFile.getName() +".");
				System.out.println("and filled out the BPA column with the name of the files that contain "
						+ "the drivers for each tupple");
				System.out.println("Please type the word done, when finished");
				boolean validEntry=false;
				do  {
					String choice = keyboard.nextLine();
					if (choice.equalsIgnoreCase("done")){
						validEntry = fileValidator.validateInput(glbpamapFile,"BPA",configurationManager);
						if (!validEntry){
							System.out.println("Please enter done when input file is complete");
						}
					}		
				} while (!validEntry);
			}	
			return true;
		}
	}
}
