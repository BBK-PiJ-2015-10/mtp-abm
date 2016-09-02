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

import configuration.ConfigurationManagerSQL;
import user.UserSpace;
import period.PeriodMaker;
import period.PeriodMakerImplSQL;

import java.util.Scanner;


public class TestPeriodMakerImplSQL {
	
	private UserSpace userSpace;
	
	private String validAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user10sql";
	
	private String validAddressPeriod = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user10sql\\period10";
	
	private File validFile = new File(validAddress);
	
	private PeriodMakerImplSQL PeriodMakerImplSQL;
	
	private String configName ="config10";
	
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
	
	@Ignore
	@Before
	public void initializeUserSpace(){
		setUpStreams();
		userSpace = new UserSpace();
		userSpace.FileSetUserSpaceFile(validFile);
		userSpace.capture("user10sql");
		PeriodMakerImplSQL = new PeriodMakerImplSQL(userSpace);
		
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////
	
//These are the test for the getUserSpace() and the Constructor with UserSpace

	/*
	 * Testing getUserSpace with valid file
	 */
	//@Ignore
	@Test
	public void testgetUserSpaceValid(){
		assertEquals(userSpace,PeriodMakerImplSQL.getUserSpace());
	}
	
	/*
	 * Testing getUserSpace with invalid file
	 */
	//@Ignore
	@Test
	public void testgetUserSpaceNotValid(){
		UserSpace userSpace2 = new UserSpace();
		assertNotEquals(userSpace2,PeriodMakerImplSQL.getUserSpace());
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////

//These are the test for validateConfiguration(String configurationName)	
	
	/*
	 * Testing validateConfiguration with a valid configuration
	 */
	//@Ignore
	@Test
	public void testValidateConfigurationValid(){
		assertEquals(true,PeriodMakerImplSQL.validateConfiguration("config10"));
	}
	
	/*
	 * Testing validateConfiguration with an invalid configuration
	 */
	//@Ignore
	@Test
	public void testValidateConfigurationinValid(){
		assertEquals(false,PeriodMakerImplSQL.validateConfiguration("config12"));
	}
	
//////////////////////////////////////////////////////////////////////////////////////

//These are the test CaptureConfiguration(Scanner sc)
	
	/*
	 * Testing captureConfiguration(Scanner sc) valid entry
	 */
	//@Ignore
	@Test
	public void testCaptureConfigurationValid(){
		autoFeedSetUp("config10");
		assertEquals("config10",PeriodMakerImplSQL.captureConfiguration(sc));
	}
	
	/*
	 * Testing captureConfiguration(Scanner sc) invalid entry twice, then valid
	 */
	//@Ignore
	@Test
	public void testCaptureConfigurationInValid(){
		autoFeedSetUpFile("testPeriodMakerImpl1SQL.txt");
		assertEquals("config10",PeriodMakerImplSQL.captureConfiguration(sc));
	}
	
//////////////////////////////////////////////////////////////////////////////////////

//These are the test creatPeriod(Scanner sc)

	/*
	 * Testing createPeriod(Scanner sc) valid
	 */
	//@Ignore
	@Test
	public void testCreatePeriodValid(){
		autoFeedSetUp("period13");
		PeriodMakerImplSQL.createPeriod(sc,configName);
		assertEquals("period13",PeriodMakerImplSQL.getPeriod().getName());
		PeriodMakerImplSQL.getPeriod().delete();
	}	
	
	/*
	 * Testing createPeriod(Scanner sc) invalid
	 */
	//@Ignore
	@Test
	public void testCreatePeriodInValid(){
		autoFeedSetUp("period13");
		PeriodMakerImplSQL.createPeriod(sc,configName);
		assertNotEquals("period12",PeriodMakerImplSQL.getPeriod().getName());
		PeriodMakerImplSQL.getPeriod().delete();
	}

	
//////////////////////////////////////////////////////////////////////////////////////

//These are the test makePeriod(Scanner sc)

	/*
	 * Testing makePeriod(Scanner sc) valid
	 */
	//@Ignore
	@Test
	public void testMakePeriodValid(){
		autoFeedSetUpFile("testPeriodMakerImpl1SQL.txt");
		PeriodMakerImplSQL.makePeriod(sc);
		assertEquals("period15",PeriodMakerImplSQL.getPeriod().getName());
		PeriodMakerImplSQL.getPeriod().delete();
	}	
	
	
//////////////////////////////////////////////////////////////////////////////////////

//These are the test capture(String periodName)
	
	/*
	 * Testing that capture valid returns true if a validName is entered
	 */
	//@Ignore
	@Test
	public void testcaptureValid(){
		PeriodMakerImplSQL validPeriod = new PeriodMakerImplSQL(new File(validAddressPeriod));
		assertEquals(true,validPeriod.capture("period10"));
	}	
	

	/*
	 * Testing that capture valid returns false if an invalidName is entered
	 */
	//@Ignore
	@Test
	public void testcaptureInValidName(){
		PeriodMakerImplSQL validPeriod = new PeriodMakerImplSQL(new File(validAddressPeriod));
		assertEquals(false,validPeriod.capture("period12"));
	}	
	
	/*
	 * Testing the returns of a valid capture by invoking a BPAFilesMap
	 */
	//@Ignore
	@Test
	public void testcaptureValidNameBPAFilesMap(){
		PeriodMakerImplSQL validPeriod = new PeriodMakerImplSQL(new File(validAddressPeriod));
		validPeriod.capture("period10");
		assertEquals(2,validPeriod.getConfiguration().getBPAFilesMap().keySet().size());
	}	
	

	/*
	 * Testing that an exception is thrown when invoking a BPAFilesMap with a non existent
	 * period file.
	 */
	//@Ignore
	@Test (expected = NullPointerException.class)
	public void testcaptureInValidNameBPAFilesMap(){
		PeriodMakerImplSQL validPeriod = new PeriodMakerImplSQL(new File(validAddressPeriod));
		validPeriod.capture("period111");
		assertEquals(2,validPeriod.getConfiguration().getBPAFilesMap().keySet().size());
	}	
	
//////////////////////////////////////////////////////////////////////////////////////

	//These are the tests for save()

	/*
	* Testing that saving stores a file on disk
	*/
	//@Ignore
	@Test
	public void testSaveValid(){
		PeriodMakerImplSQL validPeriod = new PeriodMakerImplSQL(userSpace);
		autoFeedSetUp("config10");
		validPeriod.captureConfiguration(sc);
		autoFeedSetUp("period25");
		validPeriod.createPeriod(sc,configName);
		validPeriod.save();
		String targetaddress1 =  validPeriod.getPeriod().getAbsolutePath()+"\\"+validPeriod.getPeriod().getName()+".dat";
		String targetaddress2 =  validPeriod.getPeriod().getAbsolutePath();
		File targetF = new File(targetaddress1);
		File targetD = new File(targetaddress2);
		assertEquals(true,targetF.exists());
		targetF.delete();
		targetD.delete();
	}
	
	
	/*
	* Testing that saving stores a file on disk and testing for its contents
	*/
	//@Ignore
	@Test
	public void testSaveValidTestingContent(){
		PeriodMakerImplSQL validPeriod = new PeriodMakerImplSQL(userSpace);
		autoFeedSetUp("config10");
		validPeriod.captureConfiguration(sc);
		autoFeedSetUp("period26");
		validPeriod.createPeriod(sc,configName);
		validPeriod.save();
		PeriodMakerImplSQL validPeriodtemp = new PeriodMakerImplSQL(new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user10sql\\period26"));
		validPeriodtemp.capture("period26");
		assertEquals("config10",validPeriodtemp.getConfiguration().getFile().getName());
		assertEquals(2,validPeriodtemp.getConfiguration().getBPAFilesMap().keySet().size());
		assertEquals(userSpace.getUserSpaceFile(),validPeriodtemp.getUserSpace().getUserSpaceFile());
		String targetaddress1 =  validPeriod.getPeriod().getAbsolutePath()+"\\"+validPeriod.getPeriod().getName()+".dat";
		String targetaddress2 =  validPeriod.getPeriod().getAbsolutePath();
		File targetF = new File(targetaddress1);
		File targetD = new File(targetaddress2);
		targetF.delete();
		targetD.delete();
	}
	
	
	
	

}
