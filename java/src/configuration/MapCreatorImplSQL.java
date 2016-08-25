package configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

import validator.FileValidator;
import validator.FileValidatorImplBpaMap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;



public class MapCreatorImplSQL extends MapCreatorAbstract {
			
	public MapCreatorImplSQL(boolean manualFlag){
		super(manualFlag);
	}
	
	@Override
	public boolean createMap(ConfigurationManagerAbstract configurationManager, Scanner keyboard, String mapName) {
		
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
					 PrintWriter out = new PrintWriter(bw);)
			{
				
				int size = configurationManager.getGlMainFilesAttributesMap().
						get(configurationManager.getGLFileName()).size();
				List<String> attri = configurationManager.getGlMainFilesAttributesMap().
						get(configurationManager.getGLFileName()).subList(0, size-1);
				List<Integer> attripos = new LinkedList<>();
				String line;
				Statement st = null;
				ResultSet rs = null;
				List<String> labels = new LinkedList<>();
				try {
					st = ((ConfigurationManagerSQL)configurationManager).getGLConnection().createStatement();
					rs = st.executeQuery("SELECT * FROM `"+((ConfigurationManagerSQL)configurationManager).getGLConnectionSettings().get(((ConfigurationManagerSQL)configurationManager).getGLConnectionSettings().size()-1)+"`");
					for (int i=1;i<=rs.getMetaData().getColumnCount();i++){
						if(attri.contains(rs.getMetaData().getColumnName(i))){
							attripos.add(i);
							out.write(rs.getMetaData().getColumnName(i)+",");
						}
					}	
				} catch (NoSuchElementException | IllegalStateException | SQLException ex){
					return false;
				}
				Set<String> attriset = new HashSet<>();
				out.write("BPA");
				out.println();
				while (rs.next()){
						String bpaDriver=null;
					String longword=null;
					boolean validEntry = false;
					for (Integer position : attripos){
						try {
							longword.isEmpty();
							longword =longword+" "+rs.getString(position);
						}	
						catch (NullPointerException ex){
							longword =rs.getString(position);
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
							out.write(rs.getString(position)+",");
						}
						if (!manualFlag){
							out.write(bpaDriver);
						}
					out.println();	
					}
				}
			} catch ( IOException | NoSuchElementException | SQLException ex){
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
