package configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class MapCreatorImpl implements MapCreator {

	@Override
	public void createMap(ConfigurationManager configurationManager) {
			
		File glbpamapFile = new File(configurationManager.getFile().getAbsolutePath()+"\\"+"glbpamap.csv");
		
		try {
			glbpamapFile.createNewFile();
		}
		catch (IOException ex) {
			System.out.println("Couldn't create the file");
		}
			
		try (
				FileWriter fw = new FileWriter(glbpamapFile,false);
				 BufferedWriter bw = new BufferedWriter(fw);
				 PrintWriter out = new PrintWriter(bw);
				 Scanner keyboard = new Scanner(System.in); 
				 Scanner sc = new Scanner(new FileReader(configurationManager.getGLFile()));)
		{
			int size = configurationManager.getGlMainFilesAttributesMap().
					get(configurationManager.getGLFile().getName()).size();
			List<String> attri = configurationManager.getGlMainFilesAttributesMap().
					get(configurationManager.getGLFile().getName()).subList(0, size-1);
			List<Integer> attripos = new LinkedList<>();
			for (int i=0;i<attri.size();i++){
				out.write(attri.get(i)+",");
			}
			out.write("BPA");
			out.println();
			String line;
			String[] sentence;
			sentence=sc.nextLine().split(",");
			for (int i=0;i<sentence.length;i++){
				if(attri.contains(sentence[i])){
					attripos.add(i);
				}
			}
			Set<String> attriset = new HashSet<>();
			while (sc.hasNextLine()){
				sentence=sc.nextLine().split(",");
				String bpaDriver;
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
					do {
						System.out.println("For: "+longword);
						System.out.println("Type the full name of the file with the driver data for");
						bpaDriver = keyboard.next();
						validEntry = configurationManager.getBpaFilesAttributesMap().containsKey(bpaDriver);
						if (!validEntry){
							System.out.println("File named entry doesn't exist");
						}
					} while (!validEntry);	
					for (Integer position : attripos){
						out.write(sentence[position]+",");
					}
					out.write(bpaDriver);
				}
				out.println();
			}
			sc.close();
			out.close();
		} catch (FileNotFoundException ex){
			ex.printStackTrace();
		} catch (IOException ex){
			ex.printStackTrace();
		}
		
		
	}

}
