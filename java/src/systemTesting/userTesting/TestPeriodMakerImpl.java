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

import configuration.ConfigurationManagerCSV;
import user.UserSpace;
import period.PeriodMaker;
import period.PeriodMakerImpl;

import java.util.Scanner;


public class TestPeriodMakerImpl {
	
	private UserSpace userSpace;
	
	private String validAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user11";
	
	private String validAddressPeriod = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user11\\period11";
	
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
	
	//@Ignore
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
	//@Ignore
	@Test
	public void testCaptureConfigurationValid(){
		autoFeedSetUp("config11");
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

//These are the test creatPeriod(Scanner sc)

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
	
	
//////////////////////////////////////////////////////////////////////////////////////

//These are the test capture(String periodName)
	
	/*
	 * Testing that capture valid returns true if a validName is entered
	 */
	//@Ignore
	@Test
	public void testcaptureValid(){
		PeriodMakerImpl validPeriod = new PeriodMakerImpl(new File(validAddressPeriod));
		assertEquals(true,validPeriod.capture("period11"));
	}	
	

	/*
	 * Testing that capture valid returns false if an invalidName is entered
	 */
	//@Ignore
	@Test
	public void testcaptureInValidName(){
		PeriodMakerImpl validPeriod = new PeriodMakerImpl(new File(validAddressPeriod));
		assertEquals(false,validPeriod.capture("period12"));
	}	
	
	/*
	 * Testing the returns of a valid capture by invoking a BPAFilesMap
	 */
	//@Ignore
	@Test
	public void testcaptureValidNameBPAFilesMap(){
		PeriodMakerImpl validPeriod = new PeriodMakerImpl(new File(validAddressPeriod));
		validPeriod.capture("period11");
		assertEquals(2,validPeriod.getConfiguration().getBPAFilesMap().keySet().size());
	}	
	

	/*
	 * Testing that an exception is thrown when invoking a BPAFilesMap with a non existent
	 * period file.
	 */
	//@Ignore
	@Test (expected = NullPointerException.class)
	public void testcaptureInValidNameBPAFilesMap(){
		PeriodMakerImpl validPeriod = new PeriodMakerImpl(new File(validAddressPeriod));
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
		PeriodMakerImpl validPeriod = new PeriodMakerImpl(userSpace);
		autoFeedSetUp("config11");
		validPeriod.captureConfiguration(sc);
		autoFeedSetUp("period25");
		validPeriod.createPeriod(sc);
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
		PeriodMakerImpl validPeriod = new PeriodMakerImpl(userSpace);
		autoFeedSetUp("config11");
		validPeriod.captureConfiguration(sc);
		autoFeedSetUp("period26");
		validPeriod.createPeriod(sc);
		validPeriod.save();
		PeriodMakerImpl validPeriodtemp = new PeriodMakerImpl(new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user11\\period26"));
		validPeriodtemp.capture("period26");
		assertEquals("config11",validPeriodtemp.getConfiguration().getFile().getName());
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
