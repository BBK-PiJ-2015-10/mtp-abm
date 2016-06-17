package abc.coreModels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.FileWriter;
import java.io.BufferedWriter;


public class MappingDriverSample {

	public static void main(String[] args) {
		MappingDriverSample script = new MappingDriverSample();
		script.launch();
	}
	
	public void launch(){
		
		Scanner sc = new Scanner(System.in);
		
		
		File dir = new File("user1");
		String[] outputArray = dir.list();
		for (int i=0;i<outputArray.length;i++){
			System.out.println(">> " +outputArray[i]);
		}
		
		System.out.println("Please select the name of driver file");
		String driver1=sc.next();
		System.out.println("Enter the accounts/departments driven by this driver");
		String dept=sc.next();
		
		
		
		
		//Below shows how to create a directory
		/*  
		System.out.println("Please enter a desired directory name");
		String dirname=sc.next();
		File f = new File(dirname);
		f.mkdir();
		*/		
		
		//Below shows how to read the GL file
		
		
		
		//System.out.println("Drop the General Ledger File into the User Space");
		System.out.println("Please enter the name of the General Ledger file");
		String fromFile = sc.next();
		//System.out.println("Please enter the name of the destination file");
		//String toFile = sc.next();
		String toFile = "mapper.csv";
		
		File aFile = new File("user1/"+fromFile);
		File bFile = new File("user1/"+toFile);
		sc.close();
		
		try (FileWriter fw = new FileWriter(bFile,false);
			 BufferedWriter bw = new BufferedWriter(fw);
			 PrintWriter out = new PrintWriter(bw);	
			 Scanner scanner = new Scanner(new FileReader(aFile));  	
			) 
		{	
			String line;
			//scanner.nextLine();
			Set<String> lineSet = new LinkedHashSet<>();		
			while (scanner.hasNextLine()){
				line = scanner.nextLine();
				String[] strArray = line.split(",");
				lineSet.add(strArray[1]);
			}
			for (String input: lineSet ){
				if (input.equals("Department")){
					out.println(input+","+"Driver");
				}
				else {
					if (input.equals(dept)){
						out.println(input+","+driver1);
					}
					else {
						out.println(input);
					}
				}
			}
			scanner.close();
			out.close();
		} catch (FileNotFoundException ex){
			System.out.println(" File " +aFile + " does not exist");
		} catch (IOException ex){
			ex.printStackTrace();
		}
		
		//*/	
	}
	
	
	
		
}
