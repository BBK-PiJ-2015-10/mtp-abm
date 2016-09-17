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
	* Testing runMakeNewUserSpace with valid arguments
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
	* Testing runMakeNewUserSpace with a valid UserName
	*/
	//@Ignore
	@Test
	public void testrunAccessExistingUserSpaceValid() {	
		autoFeedSetUp("user17");
		String test1 = "Please enter the name of the userspace you wish to access";
		system1 = new ABCSystemImpl(sc);
		system1.runAccessExistingUserSpace();
		assertEquals(test1,outContent.toString().trim());
	}
	
	
	/* 
	* Testing runMakeNewUserSpace with an invalid UserName
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
	* Testing runMakeNewConfiguration with a valid PeriodName
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
	* Testing runMakeNewConfiguration with an invalid PeriodName
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
	
//////////////////////////////////////////////////////////////////////////////
	
	
	//Below are the tests for runMakeNewPeriod

	/* 
	* Testing runMakeNewPeriod with a valid Arguments 
	*/
	//@Ignore
	@Test
	public void testrunGenerateReportValid() {	
		//manualFeedSetUp();
		autoFeedSetUpFile("testABCSystemImpl6.txt");
		String test1 = "Please enter the name of the userspace you wish to access";
		String test2 = "Please enter the name of the period you wish to access";
		int beg3 = test1.length()+test2.length()+4;
		String test3 = "For: SummaryClientReport, type: 1";
		int beg4 = beg3+test3.length()+2;
		String test4 = "For: DetailedClientReport, type: 2";
		int beg5 = beg4+test4.length()+2;
		String test5 = "For: SummaryBPAReport, type: 3";
		int beg6 = beg5+test5.length()+2;
		String test6 = "For: DetailedBPAReport, type: 4";
		int beg7 = beg6+test6.length()+2;
		String test7 = "Enter yes if you wish to run another report(s)";
		int beg8 = beg7+test7.length()+2;
		String test8 = "Enter no if you wish to return to the main menu";
		int beg9 = beg8+test8.length()+2;
		String test9 = "Enter exit if you wish to exit the application";	
		int end = outContent.toString().length();
		system1 = new ABCSystemImpl(sc);
		system1.runAccessExistingUserSpace();
		system1.accessExistingPeriod();
		system1.runGenerateReport();
		assertEquals(test3,outContent.toString().trim().substring(beg3,beg3+test3.length()));
		assertEquals(test4,outContent.toString().trim().substring(beg4,beg4+test4.length()));
		assertEquals(test5,outContent.toString().trim().substring(beg5,beg5+test5.length()));
		assertEquals(test6,outContent.toString().trim().substring(beg6,beg6+test6.length()));
		assertEquals(test7,outContent.toString().trim().substring(beg7,beg7+test7.length()));
		assertEquals(test8,outContent.toString().trim().substring(beg8,beg8+test8.length()));
		assertEquals(test9,outContent.toString().trim().substring(beg9,beg9+test9.length()));
	}		
	
	//run report with 2 reports.
	//delete created reports.
	//run abc
	//validSelection methods 4 tests in total.

}
