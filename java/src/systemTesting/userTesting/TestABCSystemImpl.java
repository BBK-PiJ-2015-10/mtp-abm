package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.ABCSystemImpl;

import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
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
	
////////////////////////////////////////////////////////////////////////////////
	
	
	//Below are the tests for runMakeNewUserSpace
	
	/* 
	* Testing MakeNewUserSpace with valid arguments
	*/
	//@Ignore
	@Test
	public void testrunMakeNewUserSpaceValid() {	
		autoFeedSetUpFile("testABCSystemImpl2.txt");
		system1 = new ABCSystemImpl(sc);
		assertEquals(true,system1.runMakeNewUserSpace());
		String name = "user100";
		File temp = new File(address+name);
		assertEquals(true,temp.isDirectory());
		new File(address+name+"\\"+name+".dat").delete();
		temp.delete();
	}
	
///////////////////////////////////////////////////////////////////////////////
	
	
//Below are the tests for runAccessExistingUserSpace

	/* 
	* Testing MakeNewUserSpace with a valid UserName
	*/
	//@Ignore
	@Test
	public void testrunAccessExistingUserSpaceValid() {	
		autoFeedSetUp("user18");
		String test1 = "Please enter the name of the userspace you wish to access";
		system1 = new ABCSystemImpl(sc);
		system1.runAccessExistingUserSpace();
		assertEquals(test1,outContent.toString().trim());
	}
	
	
	/* 
	* Testing MakeNewUserSpace with an invalid UserName
	*/
	//@Ignore
	@Test
	public void testrunAccessExistingUserSpaceInValidI() {	
		autoFeedSetUpFile("testABCSystemImpl3.txt");
		String test1 = "Please enter the name of the userspace you wish to access";
		String test2 = "Username entered doesn't exist, please re-enter userspace";
		system1 = new ABCSystemImpl(sc);
		system1.runAccessExistingUserSpace();
		assertEquals(test1,outContent.toString().trim().substring(0,test1.length()));
		assertEquals(test2,outContent.toString().trim().substring((test1.length()+2),
		(2+test1.length()+test2.length())));
	}
	
///////////////////////////////////////////////////////////////////////////////
	
	
	//Below are the tests for runAccessExistingPeriod
	
	/* 
	* Testing MakeNewConfiguration with a valid PeriodName
	*/
	//@Ignore
	@Test
	public void testrunAccessExistingPeriodValid() {	
		autoFeedSetUpFile("testABCSystemImpl4.txt");
		String test1 = "Please enter the name of the userspace you wish to access";
		String test2 = "Please enter the name of the period you wish to access";
		system1 = new ABCSystemImpl(sc);
		system1.runAccessExistingUserSpace();
		system1.accessExistingPeriod();
		assertEquals(test1,outContent.toString().trim().substring(0,test1.length()));
		assertEquals(test2,outContent.toString().trim().substring((test1.length()+2),
				(2+test1.length()+test2.length())));
	}	
	
	/* 
	* Testing MakeNewConfiguration with an invalid PeriodName
	*/
	//@Ignore
	@Test
	public void testrunAccessExistingPeriodInValid() {	
		autoFeedSetUpFile("testABCSystemImpl5.txt");
		String test1 = "Please enter the name of the userspace you wish to access";
		String test2 = "Please enter the name of the period you wish to access";
		int beg=test1.length()+test2.length()+4;
		String test3 = "Invalid selection";
		String test4 = "Please enter the name of the period you wish to access";
		system1 = new ABCSystemImpl(sc);
		system1.runAccessExistingUserSpace();
		system1.accessExistingPeriod();
		assertEquals(test3,outContent.toString().trim().substring(beg,beg+test3.length()));
		assertEquals(test4,outContent.toString().trim().substring(beg+test3.length()+2,
				outContent.toString().trim().length()));
	}
	
	
	
	

}
