package main;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 * A class whose purpose is to start the ABC application. It provides the
 * ability to run the application via interactive user input or by reading
 * an external data source.
 */
public class ABCStarter {
	
	private Scanner sc;
	
	private ByteArrayInputStream auto;
	
	private ABCSystem application;
	
	public void manualFeedSetUp(){
		sc = new Scanner(System.in);
	}
	
	public void autoFeedSetUpFile(String filename){
		try {
			sc = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException ex){
			System.out.println("File " + filename + " does not exist");
		} catch (IOException ex){
			ex.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		
		ABCStarter script = new ABCStarter();
		script.launch();
				
	}
	
	public void launch(){
		
		manualFeedSetUp();
		//autoFeedSetUpFile("validscriptUser10SQL.txt");
		application = new ABCSystemImpl(sc);
		application.runABC();
			
				
		
		
	}
	
	

}
