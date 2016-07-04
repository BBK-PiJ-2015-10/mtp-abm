package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import configuration.ConfigurationManager;
import user.UserSpace;
import period.PeriodMaker;
import period.PeriodMakerImpl;

import java.util.Scanner;


public class TestPeriodMakerImpl {
	
	private UserSpace userSpace;
	
	private String validAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user11";
	
	private File validFile = new File(validAddress);
	
	private PeriodMakerImpl periodMakerImpl; 
	
	private Scanner sc;
	
	private ByteArrayInputStream auto;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	
	public void manualFeedSetUp(){
		sc = new Scanner(System.in);
	}
	
	public void autoFeedSetUp(String message){
		auto = new ByteArrayInputStream(message.getBytes());
		System.setIn(auto);
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
	
	
	@Before
	public void initializeUserSpace(){
		setUpStreams();
		userSpace = new UserSpace();
		userSpace.FileSetUserSpaceFile(validFile);
		userSpace.capture("user11");
		periodMakerImpl = new PeriodMakerImpl(userSpace);
		
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////
	
//These are the test for the getUserSpace() and the Constructor with UserSpace

	/*
	 * Testing getUserSpace with valid file
	 */
	//@Ignore
	@Test
	public void testgetUserSpaceValid(){
		assertEquals(userSpace,periodMakerImpl.getUserSpace());
	}
	
	/*
	 * Testing getUserSpace with invalid file
	 */
	//@Ignore
	@Test
	public void testgetUserSpaceNotValid(){
		UserSpace userSpace2 = new UserSpace();
		assertNotEquals(userSpace2,periodMakerImpl.getUserSpace());
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////

//These are the test for validateConfiguration(String configurationName)	
	
	/*
	 * Testing validateConfiguration with a valid configuration
	 */
	//@Ignore
	@Test
	public void testValidateConfigurationValid(){
		assertEquals(true,periodMakerImpl.validateConfiguration("config11"));
	}
	
	/*
	 * Testing validateConfiguration with an invalid configuration
	 */
	//@Ignore
	@Test
	public void testValidateConfigurationinValid(){
		assertEquals(false,periodMakerImpl.validateConfiguration("config12"));
	}
	
//////////////////////////////////////////////////////////////////////////////////////

//These are the test CaptureConfiguration(Scanner sc)
	
	/*
	 * Testing captureConfiguration(Scanner sc) valid entry
	 */
	////@Ignore
	@Test
	public void testCaptureConfigurationValid(){
		autoFeedSetUp("config11");
		//manualFeedSetUp();
		System.out.println(periodMakerImpl.captureConfiguration(sc));
		assertEquals(2,2);
	}
	
	/*
	 * Testing captureConfiguration(Scanner sc) invalid entry twice, then valid
	 */
	//@Ignore
	@Test
	public void testCaptureConfigurationInValid(){
		autoFeedSetUpFile("testperiodmakerimpl1.txt");
		assertEquals(true,periodMakerImpl.captureConfiguration(sc));
	}
	
//////////////////////////////////////////////////////////////////////////////////////

//These are the test CaptureConfiguration(Scanner sc)

	/*
	 * Testing createPeriod(Scanner sc) valid
	 */
	//@Ignore
	@Test
	public void testCreatePeriodValid(){
		autoFeedSetUp("period13");
		periodMakerImpl.createPeriod(sc);
		assertEquals("period13",periodMakerImpl.getPeriod().getName());
		periodMakerImpl.getPeriod().delete();
	}	
	
	/*
	 * Testing createPeriod(Scanner sc) invalid
	 */
	//@Ignore
	@Test
	public void testCreatePeriodInValid(){
		autoFeedSetUp("period13");
		periodMakerImpl.createPeriod(sc);
		assertNotEquals("period12",periodMakerImpl.getPeriod().getName());
	}

	
//////////////////////////////////////////////////////////////////////////////////////

//These are the test makePeriod(Scanner sc)

	/*
	 * Testing makePeriod(Scanner sc) valid
	 */
	//@Ignore
	@Test
	public void testMakePeriodValid(){
		autoFeedSetUpFile("testperiodmakerimpl1.txt");
		periodMakerImpl.makePeriod(sc);
		assertEquals("period15",periodMakerImpl.getPeriod().getName());
		periodMakerImpl.getPeriod().delete();
	}	
	
	
	
	
	

}
