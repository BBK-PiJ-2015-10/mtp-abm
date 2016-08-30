package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import bpa.BpaCostsMakerImplSQL;

import period.PeriodMakerImplSQL;
import period.PeriodMaker;

public class TestBpaCostsMakerImplSQL {
	
	private String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user13sql\\period13";
	
	private File file = new File(address);
    
	private PeriodMaker period = new PeriodMakerImplSQL(file);
	
	private BpaCostsMakerImplSQL bpaCostsMakerImpl;
	
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	
	
	@Before
	public void setUp() {
		period.capture("period13");
		setUpStreams();
	}
	
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////
	
	//Below are the tests for the constructor and the getPeriod method.
	
	/*
	 * The below test compares the period entered vs the value provided by getPeriod.
	 */
	//@Ignore
	@Test
	public void testConstructorValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImplSQL(period);
		assertNotEquals(null,bpaCostsMakerImpl.getPeriodMaker());;
	}
	
	/*
	 * The below test compares the period entered vs the value provided by getPeriod.
	 */
	//@Ignore
	@Test
	public void testConstructornull(){
		bpaCostsMakerImpl = new BpaCostsMakerImplSQL(null);
		assertEquals(null,bpaCostsMakerImpl.getPeriodMaker());;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////	
	
	//Below are the tests for the constructor and the getPeriod method.
	
	/*
	 * Test that if a null or invalid period is encapsulated the method returns false.
	 */
	//@Ignore
	@Test
	public void DisplayInputFilesInValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImplSQL(null);
		assertEquals(false,bpaCostsMakerImpl.displayInputFilesNames());
	}	

	/*
	 * Test that if a valid period is encapsulated the method returns true. 
	 */
	//@Ignore
	@Test
	public void DisplayInputFilesValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImplSQL(period);
		assertEquals(true,bpaCostsMakerImpl.displayInputFilesNames());
	}	
	
	
/////////////////////////////////////////////////////////////////////////////////////////	
	
//Below are the tests for the putToSleep method.

	/*
	* Test that if a valid time is entered the method returns true.
	*/
	//@Ignore
	@Test
	public void testPutToSleepValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImplSQL(period);
		assertEquals(true,bpaCostsMakerImpl.putToSleep(30));
	}	
	
	/*
	* Test that if a valid time is entered the method returns true.
	*/
	//@Ignore
	@Test
	public void testPutToSleepInValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImplSQL(period);
		assertEquals(false,bpaCostsMakerImpl.putToSleep(-30));
	}
	
//////////////////////////////////////////////////////////////////////////////////////////
	
	//Below are the tests for validateInput

	/*
	* Test that if all files are present the method returns true.
	*/
	//@Ignore
	@Test
	public void testValidateInputValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImplSQL(period);
		assertEquals(true,bpaCostsMakerImpl.validateInput(bpaCostsMakerImpl.getPeriodMaker().getPeriodFiles()));
	}	
	
	/*
	* Test that if no files are present the method returns false as well as the messages generated
	* by the system.
	*/
	//@Ignore
	@Test
	public void testValidateInputEmpty(){
		String eaddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user13sql\\emptyperiod";
		File efile = new File(eaddress);
		PeriodMaker eperiod = new PeriodMakerImplSQL(efile);
		eperiod.capture("emptyperiod");
		eperiod.save();
	    bpaCostsMakerImpl = new BpaCostsMakerImplSQL(eperiod);
	    assertEquals(false,bpaCostsMakerImpl.validateInput(bpaCostsMakerImpl.getPeriodMaker().getPeriodFiles()));
	    String str1 = "The file named: implementation.csv is missing";
	    String str2 = "The file named: phones.csv is missing";
	    int beg,end=0;
	    assertEquals(str1,outContent.toString().trim().substring(0,str1.length()));
	    beg=str1.length()+2;
	    end=str1.length()+2+str2.length();
	    assertEquals(str2,outContent.toString().trim().substring(beg,end));	    
	}
		
	/*
	* Test that if no drivers files are present the method returns false.
	*/
	//@Ignore
	@Test
	public void testValidateInputPartialNoDriversFiles(){
		String paraddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user13sql\\partialperiod";
		File parfile = new File(paraddress);
		PeriodMaker parperiod = new PeriodMakerImplSQL(parfile);
		parperiod.capture("partialperiod");
		parperiod.save();
	    bpaCostsMakerImpl = new BpaCostsMakerImplSQL(parperiod);
	    assertEquals(false,bpaCostsMakerImpl.validateInput(bpaCostsMakerImpl.getPeriodMaker().getPeriodFiles()));
	    String str1 = "The file named: phones.csv is missing";
	    assertEquals(str1,outContent.toString().trim().substring(0,str1.length()));
	}
		

///////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Below are the tests for extractGLBPAMap() and getDriversMap()

	/*
	* Test extract GLBPAMap with valid input.
	*/
	//@Ignore
	@Test
	public void testExtractGLPBPAMapValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImplSQL(period);
		assertEquals(true,bpaCostsMakerImpl.extractGLBPAMap(bpaCostsMakerImpl.getPeriodMaker().getDriversMap()));
	}	
	
	
	/*
	* Test extract GLBPAMap with valid a null period.
	*/
	//@Ignore
	@Test
	public void testExtractGLPBPAMapValidNull(){
		bpaCostsMakerImpl = new BpaCostsMakerImplSQL(null);
		assertEquals(false,bpaCostsMakerImpl.extractGLBPAMap(null));
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////
	
	//Below are the tests for createBpaCosts() and getBPACosts()

	/*
	* Test createBpaCosts with valid inputs.
	*/
	//@Ignore
	@Test
	public void testcreateBpaCostsValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImplSQL(period);
		assertEquals(true,bpaCostsMakerImpl.createBpaCostsFile());
		assertEquals("bpaCosts.csv",bpaCostsMakerImpl.getBPACosts().getName());
	}
	
	/*
	* Test createBpaCosts with valid inputs.
	*/
	//@Ignore
	@Test (expected = NullPointerException.class)
	public void testcreateBpaCostsNull(){
		bpaCostsMakerImpl = new BpaCostsMakerImplSQL(null);
		bpaCostsMakerImpl.createBpaCostsFile();
		assertEquals("bpaCosts.csv",bpaCostsMakerImpl.getBPACosts().getName());
	}
	
///////////////////////////////////////////////////////////////////////////////////////
		
	//Below are the tests for extractGl 
	
	/*
	* Test extractGL with valid inputs.
	*/
	//@Ignore
	@Test
	public void testExtractGLValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImplSQL(period);
		bpaCostsMakerImpl.validateInput(bpaCostsMakerImpl.getPeriodMaker().getPeriodFiles());
		bpaCostsMakerImpl.extractGLBPAMap(bpaCostsMakerImpl.getPeriodMaker().getDriversMap());
		bpaCostsMakerImpl.createBpaCostsFile();
		assertEquals(true,bpaCostsMakerImpl.processGL(bpaCostsMakerImpl.getPeriodMaker().getPeriodFiles(),bpaCostsMakerImpl.getPeriodMaker().getDriversMap()));
		assertEquals(true,bpaCostsMakerImpl.getBPACosts().exists());
		assertEquals(true,bpaCostsMakerImpl.getBPACosts().delete());
	}	
	
	/*
	* Test extractGL with missing populated data structures
	*/
	//@Ignore
	@Test
	public void testExtractGLNull(){
		bpaCostsMakerImpl = new BpaCostsMakerImplSQL(period);
		assertEquals(false,bpaCostsMakerImpl.processGL(null,null));
	}
	
	
////////////////////////////////////////////////////////////////////////////////////
	
	
	//Below are the tests for createbpaCosts();
	
	/*
	* Test createBpaCosts with valid inputs
	*/
	//@Ignore
	@Test
	public void testCreateBpaCostsValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImplSQL(period);
		assertEquals(true,bpaCostsMakerImpl.createbpaCosts());
		assertEquals(true,bpaCostsMakerImpl.getBPACosts().exists());
		assertEquals(true,bpaCostsMakerImpl.getBPACosts().delete());
	}	
	
	/*
	* Test createBpaCosts with a null period
	*/
	//@Ignore
	@Test
	public void testCreateBpaCosts(){
		bpaCostsMakerImpl = new BpaCostsMakerImplSQL(null);
		assertEquals(false,bpaCostsMakerImpl.createbpaCosts());
	}
	
	
	
	
	
	
}
