package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.ABCSystemImpl;

import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class TestABCSystemImpl {
	
	private ABCSystemImpl system1;
	
	private ByteArrayInputStream auto;
	
	private Scanner sc;
	
	private String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\";
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	@Before
	public void initialize(){
		//system1 = new ABCSystemImpl(sc);
		setUpStreams();
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}	
	
	
	public void manualFeedSetUp(){
		sc = new Scanner(System.in);
		system1 = new ABCSystemImpl(sc);
	}
	
	public void autoFeedSetUp(String message){
		auto = new ByteArrayInputStream(message.getBytes());
		System.setIn(auto);
		sc = new Scanner(System.in);
		system1 = new ABCSystemImpl(sc);
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
	
	
	/* 
	* Testing MakeNewUserSpace with valid arguments
	*/
	//@Ignore
	@Test
	public void testrunMakeNewUserSpaceValid1() {	
		autoFeedSetUpFile("testABCSystemImpl1.txt");
		system1 = new ABCSystemImpl(sc);
		assertEquals(true,system1.runMakeNewUserSpace());
		String name = "user100";
		File temp = new File(address+name);
		assertEquals(true,temp.isDirectory());
		new File(address+name+"\\"+name+".dat").delete();
		temp.delete();
	}
	
	
	
	

}
