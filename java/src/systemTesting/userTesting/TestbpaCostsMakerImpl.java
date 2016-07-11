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

import bpa.BpaCostsMakerImpl;
import period.PeriodMaker;
import period.PeriodMakerImpl;

public class TestbpaCostsMakerImpl {
	
	private String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user14\\period14";
	
	private File file = new File(address);
    
	private PeriodMaker period = new PeriodMakerImpl(file);
	
	private BpaCostsMakerImpl bpaCostsMakerImpl;
	
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	
	
	@Before
	public void setUp() {
		period.capture("period14");
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
		bpaCostsMakerImpl = new BpaCostsMakerImpl(period);
		assertNotEquals(null,bpaCostsMakerImpl.getPeriodMaker());;
	}
	
	/*
	 * The below test compares the period entered vs the value provided by getPeriod.
	 */
	//@Ignore
	@Test
	public void testConstructornull(){
		bpaCostsMakerImpl = new BpaCostsMakerImpl(null);
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
		bpaCostsMakerImpl = new BpaCostsMakerImpl(null);
		assertEquals(false,bpaCostsMakerImpl.displayInputFilesNames());
	}	

	/*
	 * Test that if a valid period is encapsulated the method returns true. 
	 */
	//@Ignore
	@Test
	public void DisplayInputFilesValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImpl(period);
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
		bpaCostsMakerImpl = new BpaCostsMakerImpl(period);
		assertEquals(true,bpaCostsMakerImpl.putToSleep(30));
	}	
	
	/*
	* Test that if a valid time is entered the method returns true.
	*/
	//@Ignore
	@Test
	public void testPutToSleepInValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImpl(period);
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
		bpaCostsMakerImpl = new BpaCostsMakerImpl(period);
		assertEquals(true,bpaCostsMakerImpl.validateInput());
	}	
	
	/*
	* Test that if no files are present the method returns false.
	*/
	//@Ignore
	@Test
	public void testValidateInputEmpty(){
		String eaddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user14\\emptyperiod";
		File efile = new File(eaddress);
		PeriodMaker eperiod = new PeriodMakerImpl(efile);
		eperiod.capture("emptyperiod");
		eperiod.save();
	    bpaCostsMakerImpl = new BpaCostsMakerImpl(eperiod);
	    assertEquals(false,bpaCostsMakerImpl.validateInput());
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
		String paraddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user14\\partialperiod";
		File parfile = new File(paraddress);
		PeriodMaker parperiod = new PeriodMakerImpl(parfile);
		parperiod.capture("partialperiod");
		parperiod.save();
	    bpaCostsMakerImpl = new BpaCostsMakerImpl(parperiod);
	    assertEquals(false,bpaCostsMakerImpl.validateInput());
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
		String paraddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user14\\partialperiodnogl";
		File parfile = new File(paraddress);
		PeriodMaker parperiod = new PeriodMakerImpl(parfile);
		parperiod.capture("partialperiodnogl");
		parperiod.save();
	    bpaCostsMakerImpl = new BpaCostsMakerImpl(parperiod);
	    assertEquals(false,bpaCostsMakerImpl.validateInput());
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
		bpaCostsMakerImpl = new BpaCostsMakerImpl(period);
		assertEquals(true,bpaCostsMakerImpl.extractGLBPAMap());
		assertEquals(5,bpaCostsMakerImpl.getDriversMap().keySet().size());
		assertEquals(5,bpaCostsMakerImpl.getDriversMap().values().size());
	}	
	
	
	/*
	* Test extract GLBPAMap with valid a null period.
	*/
	//@Ignore
	@Test
	public void testExtractGLPBPAMapValidNull(){
		bpaCostsMakerImpl = new BpaCostsMakerImpl(null);
		assertEquals(false,bpaCostsMakerImpl.extractGLBPAMap());
		assertEquals(0,bpaCostsMakerImpl.getDriversMap().keySet().size());
		assertEquals(0,bpaCostsMakerImpl.getDriversMap().values().size());
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////
	
	//Below are the tests for createBpaCosts() and getBPACosts()

	/*
	* Test createBpaCosts with valid inputs.
	*/
	//@Ignore
	@Test
	public void testcreateBpaCostsValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImpl(period);
		assertEquals(true,bpaCostsMakerImpl.createBpaCostsFile());
		assertEquals("bpaCosts.csv",bpaCostsMakerImpl.getBPACosts().getName());
	}
	
	/*
	* Test createBpaCosts with valid inputs.
	*/
	//@Ignore
	@Test (expected = NullPointerException.class)
	public void testcreateBpaCostsNull(){
		bpaCostsMakerImpl = new BpaCostsMakerImpl(null);
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
		bpaCostsMakerImpl = new BpaCostsMakerImpl(period);
		bpaCostsMakerImpl.validateInput();
		bpaCostsMakerImpl.extractGLBPAMap();
		bpaCostsMakerImpl.createBpaCostsFile();
		assertEquals(true,bpaCostsMakerImpl.extractGL());
		assertEquals(true,bpaCostsMakerImpl.getBPACosts().exists());
	}	
	
	/*
	* Test extractGL with missing populated data structures
	*/
	//@Ignore
	@Test
	public void testExtractGLNull(){
		bpaCostsMakerImpl = new BpaCostsMakerImpl(period);
		assertEquals(false,bpaCostsMakerImpl.extractGL());
	}
	
	
////////////////////////////////////////////////////////////////////////////////////
	
	
	//Below are the tests for createbpaCosts();
	
	
	
	
	
}
