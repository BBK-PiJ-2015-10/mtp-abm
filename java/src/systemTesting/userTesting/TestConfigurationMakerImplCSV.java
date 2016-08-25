package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import configuration.ConfigurationMaker;
import configuration.ConfigurationMakerImplCSV;
import user.UserSpace;

public class TestConfigurationMakerImplCSV {
	
	private UserSpace userSpace;
	
	private String validAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user12\\";
	
	private File validFile = new File(validAddress);
	
	private ByteArrayInputStream auto;
	
	private Scanner sc;
	
	private ConfigurationMaker configurationMaker = new ConfigurationMakerImplCSV();
	
	private ConfigurationMakerImplCSV tempCMI = new ConfigurationMakerImplCSV();
	
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
	}
	

//////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * Testing captureInput(String input, Scanner sc)
	 */
	
	//@Ignore
	@Test
	public void testcaptureValidInput() {
		String feeder ="Ale Cane";
		autoFeedSetUp(feeder);
		userSpace = new UserSpace();
		userSpace.FileSetUserSpaceFile(validFile);
		ConfigurationMakerImplCSV tempCMI = new ConfigurationMakerImplCSV();
		assertEquals(feeder,tempCMI.captureInput("Enter Something", sc));
	}
	
	
	//@Ignore
	@Test (expected = NullPointerException.class)
	public void testcaptureEmptyScanner() {
		String feeder ="Ale Cane";
		autoFeedSetUp(feeder);
		userSpace = new UserSpace();
		userSpace.FileSetUserSpaceFile(validFile);
		ConfigurationMakerImplCSV tempCMI = new ConfigurationMakerImplCSV();
		assertEquals(feeder,tempCMI.captureInput("Enter Something", null));
	}
	

	//@Ignore
	@Test
	public void testcaptureEmptyString() {
		String feeder ="Ale Cane";
		autoFeedSetUp(feeder);
		userSpace = new UserSpace();
		userSpace.FileSetUserSpaceFile(validFile);
		ConfigurationMakerImplCSV tempCMI = new ConfigurationMakerImplCSV();
		assertEquals(feeder,tempCMI.captureInput(null,sc));
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////	
	
	/*
	* Testing makeConfiguration without a config name
	*/
	//@Ignore
	@Test
	public void testmakeConfigurationIncompleteScanning() {
		autoFeedSetUpFile("testconfigurationmakerimpl1.txt");
		userSpace = new UserSpace();
		userSpace.FileSetUserSpaceFile(validFile);
		assertEquals(false,configurationMaker.makeConfiguration(userSpace, sc));
	}
	
	/*
	* Testing makeConfiguration without a null scanner.
	*/
	//@Ignore
	@Test
	public void testmakeConfigurationNullScanner() {
		autoFeedSetUpFile("testconfigurationmakerimpl2.txt");
		userSpace = new UserSpace();
		userSpace.FileSetUserSpaceFile(validFile);
		assertEquals(false,configurationMaker.makeConfiguration(userSpace, null));
	}
	
	/*
	* Testing makeConfiguration with a null UserSpace
	*/
	//@Ignore
	@Test
	public void testmakeConfigurationNullUserSpace() {
		autoFeedSetUpFile("testconfigurationmakerimpl2.txt");
		userSpace = new UserSpace();
		userSpace.FileSetUserSpaceFile(validFile);
		assertEquals(false,configurationMaker.makeConfiguration(null, sc));
	}
	
	/*
	* Testing makeConfiguration without input files
	*/
	//@Ignore
	@Test
	public void testmakeConfigurationMissingInputs() {
		autoFeedSetUpFile("testconfigurationmakerimpl2.txt");
		userSpace = new UserSpace();
		userSpace.FileSetUserSpaceFile(validFile);
		assertEquals(false,configurationMaker.makeConfiguration(userSpace, sc));
	}
	

}
