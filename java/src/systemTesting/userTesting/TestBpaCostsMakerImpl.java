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

import bpa.BpaCostsMakerImplCSV;
import old.PeriodMakerImpl;
import period.PeriodMaker;

public class TestBpaCostsMakerImpl {
	
	private String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user13\\period13";
	
	private File file = new File(address);
    
	private PeriodMaker period = new PeriodMakerImpl(file);
	
	private BpaCostsMakerImplCSV bpaCostsMakerImpl;
	
	
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
		bpaCostsMakerImpl = new BpaCostsMakerImplCSV(period);
		assertNotEquals(null,bpaCostsMakerImpl.getPeriodMaker());;
	}
	
	/*
	 * The below test compares the period entered vs the value provided by getPeriod.
	 */
	//@Ignore
	@Test
	public void testConstructornull(){
		bpaCostsMakerImpl = new BpaCostsMakerImplCSV(null);
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
		bpaCostsMakerImpl = new BpaCostsMakerImplCSV(null);
		assertEquals(false,bpaCostsMakerImpl.displayInputFilesNames());
	}	

	/*
	 * Test that if a valid period is encapsulated the method returns true. 
	 */
	//@Ignore
	@Test
	public void DisplayInputFilesValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImplCSV(period);
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
		bpaCostsMakerImpl = new BpaCostsMakerImplCSV(period);
		assertEquals(true,bpaCostsMakerImpl.putToSleep(30));
	}	
	
	/*
	* Test that if a valid time is entered the method returns true.
	*/
	//@Ignore
	@Test
	public void testPutToSleepInValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImplCSV(period);
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
		bpaCostsMakerImpl = new BpaCostsMakerImplCSV(period);
		assertEquals(true,bpaCostsMakerImpl.validateInput(bpaCostsMakerImpl.getPeriodMaker().getPeriodFiles()));
	}	
	
	/*
	* Test that if no files are present the method returns false.
	*/
	//@Ignore
	@Test
	public void testValidateInputEmpty(){
		String eaddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user13\\emptyperiod";
		File efile = new File(eaddress);
		PeriodMaker eperiod = new PeriodMakerImpl(efile);
		eperiod.capture("emptyperiod");
		eperiod.save();
	    bpaCostsMakerImpl = new BpaCostsMakerImplCSV(eperiod);
	    assertEquals(false,bpaCostsMakerImpl.validateInput(bpaCostsMakerImpl.getPeriodMaker().getPeriodFiles()));;
	    String str1 = "The file named: implementation.csv is missing";
	    String str2 = "The file named: phones.csv is missing";
	    String str3 = "The file named: gl.csv is missing";
	    int beg,end=0;
	    assertEquals(str1,outContent.toString().trim().substring(0,str1.length()));
	    beg=str1.length()+2;
	    end=str1.length()+2+str2.length();
	    assertEquals(str2,outContent.toString().trim().substring(beg,end));
	    assertEquals(str3,outContent.toString().trim().substring(end+2));	    
	}
		
	/*
	* Test that if no drivers files are present the method returns false.
	*/
	//@Ignore
	@Test
	public void testValidateInputPartialNoDriversFiles(){
		String paraddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user13\\partialperiod";
		File parfile = new File(paraddress);
		PeriodMaker parperiod = new PeriodMakerImpl(parfile);
		parperiod.capture("partialperiod");
		parperiod.save();
	    bpaCostsMakerImpl = new BpaCostsMakerImplCSV(parperiod);
	    assertEquals(false,bpaCostsMakerImpl.validateInput(bpaCostsMakerImpl.getPeriodMaker().getPeriodFiles()));
	    String str1 = "The file named: implementation.csv is missing";
	    String str2 = "The file named: phones.csv is missing";
	    int total = str1.length()+str2.length();
	    assertEquals(str1,outContent.toString().trim().substring(0,str1.length()));
	    assertEquals(str2,outContent.toString().trim().substring(str1.length()+2));
	}
	
	/*
	* Test that if no GL file is present the method returns false.
	*/
	//@Ignore
	@Test
	public void testValidateInputPartialNoGLFile(){
		String paraddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user13\\partialperiodnogl";
		File parfile = new File(paraddress);
		PeriodMaker parperiod = new PeriodMakerImpl(parfile);
		parperiod.capture("partialperiodnogl");
		parperiod.save();
	    bpaCostsMakerImpl = new BpaCostsMakerImplCSV(parperiod);
	    assertEquals(false,bpaCostsMakerImpl.validateInput(bpaCostsMakerImpl.getPeriodMaker().getPeriodFiles()));
	    String result = "The file named: gl.csv is missing";
	    assertEquals(result,outContent.toString().substring(0,result.length()));
	}
	

///////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Below are the tests for extractGLBPAMap() and getDriversMap()

	/*
	* Test extract GLBPAMap with valid input.
	*/
	//@Ignore
	@Test
	public void testExtractGLPBPAMapValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImplCSV(period);
		assertEquals(true,bpaCostsMakerImpl.extractGLBPAMap(bpaCostsMakerImpl.getPeriodMaker().getDriversMap()));
	}	
	
	
	/*
	* Test extract GLBPAMap with valid a null period.
	*/
	//@Ignore
	@Test
	public void testExtractGLPBPAMapValidNull(){
		bpaCostsMakerImpl = new BpaCostsMakerImplCSV(null);
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
		bpaCostsMakerImpl = new BpaCostsMakerImplCSV(period);
		assertEquals(true,bpaCostsMakerImpl.createBpaCostsFile());
		assertEquals("bpaCosts.csv",bpaCostsMakerImpl.getBPACosts().getName());
	}
	
	/*
	* Test createBpaCosts with valid inputs.
	*/
	//@Ignore
	@Test (expected = NullPointerException.class)
	public void testcreateBpaCostsNull(){
		bpaCostsMakerImpl = new BpaCostsMakerImplCSV(null);
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
		bpaCostsMakerImpl = new BpaCostsMakerImplCSV(period);
		bpaCostsMakerImpl.validateInput(bpaCostsMakerImpl.getPeriodMaker().getPeriodFiles());
		bpaCostsMakerImpl.extractGLBPAMap(bpaCostsMakerImpl.getPeriodMaker().getDriversMap());
		bpaCostsMakerImpl.createBpaCostsFile();
		assertEquals(true,bpaCostsMakerImpl.extractGL(bpaCostsMakerImpl.getPeriodMaker().getPeriodFiles(),bpaCostsMakerImpl.getPeriodMaker().getDriversMap()));
		assertEquals(true,bpaCostsMakerImpl.getBPACosts().exists());
		assertEquals(true,bpaCostsMakerImpl.getBPACosts().delete());
	}	
	
	/*
	* Test extractGL with missing populated data structures
	*/
	//@Ignore
	@Test
	public void testExtractGLNull(){
		bpaCostsMakerImpl = new BpaCostsMakerImplCSV(period);
		assertEquals(false,bpaCostsMakerImpl.extractGL(null,null));
	}
	
	
////////////////////////////////////////////////////////////////////////////////////
	
	
	//Below are the tests for createbpaCosts();
	
	/*
	* Test createBpaCosts with valid inputs
	*/
	//@Ignore
	@Test
	public void testCreateBpaCostsValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImplCSV(period);
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
		bpaCostsMakerImpl = new BpaCostsMakerImplCSV(null);
		assertEquals(false,bpaCostsMakerImpl.createbpaCosts());
	}
	
	
	
	
	
	
}
