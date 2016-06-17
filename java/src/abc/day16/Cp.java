package abc.day16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Cp {

	public static void main(String[] args) {
		
		Cp c = new Cp();
		c.launch();
		
	}
	
	public void launch(){
		
		Scanner sc = new Scanner (System.in);
		System.out.println("Please enter the name of the source file");
		String fromFile = sc.next();
		System.out.println("Please enter the name of the destination file");
		String toFile = sc.next();
		
		File aFile = new File(fromFile);
		File bFile = new File(toFile);
		
		if (bFile.exists()){
			
			//System.out.println("Do you want to overwrite " + bFile + "? y/n");
            //String str = System.console().readLine();
            //if (str.equals("n")) {
                //System.out.println("File not copied");
                //return;
            //}
		
			try  (PrintWriter out = new PrintWriter(bFile);
                  BufferedReader in = new BufferedReader(new FileReader(aFile));) 
               {
                  String line;
                  while ((line = in.readLine()) != null) {
                     out.println(line);
                  }
               }  
               catch (FileNotFoundException ex) {
                   System.out.println("File " + aFile + " does not exist");
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
           }
		
		System.out.println("This is Done");
		
	}
	

}
